package com.khpl.uzikbbang.api.service.auth;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;
import javax.transaction.Transactional;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.api.controller.auth.Session;
import com.khpl.uzikbbang.api.controller.auth.request.SignIn;
import com.khpl.uzikbbang.api.controller.auth.request.SignOut;
import com.khpl.uzikbbang.api.controller.auth.request.SignUp;
import com.khpl.uzikbbang.config.AppConfig;
import com.khpl.uzikbbang.config.crypto.PasswordEncoder;
import com.khpl.uzikbbang.domain.user.User;
import com.khpl.uzikbbang.exception.AlreadySignUpEmailException;
import com.khpl.uzikbbang.exception.BadCredentialsException;
import com.khpl.uzikbbang.exception.InvalidSignInException;
import com.khpl.uzikbbang.exception.Unauthorized;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AppConfig appConfig;

    public User signUp(SignUp signUp) {
        String email = signUp.getEmail();

        // 이메일 중복 체크
        userService.findByEmail(email).ifPresent(user -> {
            throw new AlreadySignUpEmailException();
        });

        String password = passwordEncoder.encrypt(signUp.getPassword());

        User user = User.builder()
                .name(signUp.getName())
                .email(email)
                .password(password)
                .build();

        userService.save(user);

        return user;
    }

    @Transactional
    public User signIn(SignIn signIn) {

        User user = userService.findByEmail(signIn.getEmail())
                .orElseThrow(InvalidSignInException::new);

        // 차단된 사용자 확인
        if(user.isUseAt() == false) {
            throw new InvalidSignInException();
        }

        boolean isMatches = passwordEncoder.matches(signIn.getPassword(), user.getPassword());

        if (isMatches == false) {
            throw new InvalidSignInException();
        }

        user.addSession();

        return user;
    }

    @Transactional
    public Session getSession(Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(InvalidSignInException::new);

        return user.addSession();
    }

    @Transactional
    public User signOut(SignOut signOut) {
        String email = signOut.getEmail();

        Optional<User> optUser = userService.findByEmail(email);

        if (optUser.isPresent() == false) {
            throw new Unauthorized();
        }

        User user = optUser.get();

        String password = user.getPassword();
        boolean isMatches = passwordEncoder.matches(signOut.getPassword(), password);
        if (isMatches) {
            user.setRefreshToken("");
            return user;
        } else {
            throw new Unauthorized();
        }
    }

    public String createAccessToken(Long userId) {
        SecretKey secretKey = Keys.hmacShaKeyFor(appConfig.getAuthKey());

        long now = System.currentTimeMillis();
        long tenMin = Duration.ofMinutes(10).toMillis();

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .signWith(secretKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(now + tenMin))
                .compact();
    }

    @Transactional
    public String createRefreshToken(Long userId) {
        SecretKey secretKey = Keys.hmacShaKeyFor(appConfig.getAuthKey());

        long now = System.currentTimeMillis();
        long sevenDays = Duration.ofDays(7).toMillis();

        String refreshToken = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .signWith(secretKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(now + sevenDays))
                .compact();

        User user = userService.findById(userId)
                .orElseThrow(BadCredentialsException::new);

        user.setRefreshToken(refreshToken);

        return refreshToken;
    }

    public ResponseCookie createCookie(String refreshToken, int days) {
        long sevenDays = Duration.ofDays(days).toMillis();

        return ResponseCookie.from("refreshToken", refreshToken)
                .domain("localhost")
                .path("/")
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .maxAge(sevenDays)
                .build();
    }
}
