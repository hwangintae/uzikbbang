package com.khpl.uzikbbang.controller;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.request.SignIn;
import com.khpl.uzikbbang.request.SignUp;
import com.khpl.uzikbbang.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Value("${auth.key}")
    private String AUTH_KEY;
    
    @PostMapping(value = "/signup")
    public void signUp(@RequestBody SignUp signUp) {
        authService.signUp(signUp);
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<Object> singIn (@RequestBody SignIn signIn) {
        UzikUser user = authService.signIn(signIn);

        Long userId = user.getId();

        byte[] decode = Base64.getDecoder().decode(AUTH_KEY);

        SecretKey secretKey = Keys.hmacShaKeyFor(decode);

        String jws = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .signWith(secretKey)
                .setIssuedAt(new Date())
                .compact();

        ResponseCookie cookie = ResponseCookie.from("accessToken", jws)
            .domain("localhost") // TODO 개발 환경에 맞게 분리해야함
            .path("/")
            .httpOnly(true)
            .secure(false)
            .sameSite("Strict")
            .maxAge(Duration.ofDays(30))
        .build();
                
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .build();
    }
    
}
