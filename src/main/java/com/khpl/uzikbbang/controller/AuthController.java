package com.khpl.uzikbbang.controller;

import java.time.Duration;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khpl.uzikbbang.config.AppConfig;
import com.khpl.uzikbbang.config.TokenParser;
import com.khpl.uzikbbang.config.data.UserSession;
import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.request.RefreshRequest;
import com.khpl.uzikbbang.request.SignIn;
import com.khpl.uzikbbang.request.SignUp;
import com.khpl.uzikbbang.response.SessionResponse;
import com.khpl.uzikbbang.service.AuthService;
import com.khpl.uzikbbang.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AppConfig appConfig;
    private final TokenParser tokenParser;

    private final UserService userService;
    
    @PostMapping(value = "/signup")
    public void signUp(@RequestBody SignUp signUp) {
        authService.signUp(signUp);
    }

    // CHECKLIST
    // [v] refresh token 만들기
    // [v] refresh token은 cookie에 저장
    // [v] access token 갱신 주기 짧게 하기
    // [v] access token은 json으로 리턴한다.
    @PostMapping(value = "/signin")
    public ResponseEntity<SessionResponse> singIn (@RequestBody SignIn signIn) {
        UzikUser user = authService.signIn(signIn);
        Long userId = user.getId();

        SecretKey secretKey = Keys.hmacShaKeyFor(appConfig.getAuthKey());

        long now = System.currentTimeMillis();
        long tenMin = Duration.ofMinutes(10).toMillis();
        long sevenDays = Duration.ofDays(7).toMillis();

        String accessToken = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .signWith(secretKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(now + tenMin))
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .signWith(secretKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(now + sevenDays))
                .compact();
                
        user.setRefreshToken(refreshToken);
        userService.save(user);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
            .domain("localhost") // TODO 개발 환경에 맞게 분리해야함
            .path("/")
            .httpOnly(true)
            .secure(false)
            .sameSite("Strict")
            .maxAge(Duration.ofDays(7))
        .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new SessionResponse(accessToken));
    }

    @GetMapping("/foo")
    public Long foo(UserSession userSession) {
        return userSession.getId();
    }

    @PostMapping("/refresh")
    public void refresh(HttpServletRequest httpServletRequest) {
        RefreshRequest request = RefreshRequest.builder()
            .httpServletRequest(httpServletRequest)
            .tokenParser(tokenParser)
        .build();

        request.valid();

        String token = request.getToken();
        Claims claims = request.getClaims(token);        
        UserSession userSession = tokenParser.getUserSession(claims);
        
        boolean isInValidRefresh = request.isInValidRefresh(httpServletRequest);
        Long id = userSession.getId();

        if (isInValidRefresh) {
            UzikUser user = userService.findById(id);
        }
    }
    
}
