package com.khpl.uzikbbang.config;

import java.time.Duration;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.http.ResponseCookie;

import io.jsonwebtoken.Jwts;

public class Auth {
    private final SecretKey secretKey;
    private final Long userId;

    long now = System.currentTimeMillis();
    long tenMin = Duration.ofMinutes(10).toMillis();
    long sevenDays = Duration.ofDays(7).toMillis();

    public Auth(SecretKey secretKey, Long userId) {
        this.secretKey = secretKey;
        this.userId = userId;
    }

    public String getAccessToken() {
        return Jwts.builder()
            .setSubject(String.valueOf(this.userId))
            .signWith(this.secretKey)
            .setIssuedAt(new Date())
            .setExpiration(new Date(now + tenMin))
            .compact();
    }

    public String getRefreshToken() {
        return Jwts.builder()
            .setSubject(String.valueOf(this.userId))
            .signWith(this.secretKey)
            .setIssuedAt(new Date())
            .setExpiration(new Date(now + sevenDays))
            .compact();
    }

    public ResponseCookie getCookie(String refreshToken) {
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
