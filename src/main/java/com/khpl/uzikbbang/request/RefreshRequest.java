package com.khpl.uzikbbang.request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.khpl.uzikbbang.config.TokenParser;
import com.khpl.uzikbbang.exception.BadCredentialsException;

import io.jsonwebtoken.Claims;
import lombok.Builder;

public class RefreshRequest {
    private final HttpServletRequest httpServletRequest;
    private final TokenParser tokenParser;

    @Builder
    public RefreshRequest(HttpServletRequest httpServletRequest, TokenParser tokenParser) {
        this.httpServletRequest = httpServletRequest;
        this.tokenParser = tokenParser;
    }

    public String getToken() {
        Cookie[] cookies = this.httpServletRequest.getCookies();
        String refreshToken = cookies[0].getValue();

        return refreshToken;
    }

    public Claims getClaims(String refreshToken) {
        return tokenParser.parse(refreshToken);
    }

    public void valid() {
        validateRequest(this.httpServletRequest);

        Cookie[] cookies = this.httpServletRequest.getCookies();
        validateCookies(cookies);

        String refreshToken = cookies[0].getValue();
        validateRefreshToken(refreshToken);

        String accessToken = httpServletRequest.getHeader("Authorization");
        validateAccessToken(accessToken);
    }

    public void validateRequest(HttpServletRequest httpServletRequest) {
        if (httpServletRequest == null) {
            throw new BadCredentialsException();
        }
    }

    public void validateCookies(Cookie[] cookies) {
        if (cookies.length == 0) {
            throw new BadCredentialsException();
        }
    }

    public void validateRefreshToken(String refreshToken) {
        if (refreshToken.equals("") || refreshToken == null) {
            throw new BadCredentialsException();
        }

        Claims claims = tokenParser.parse(refreshToken);
        if (claims.getExpiration().getTime() < System.currentTimeMillis()) {
            throw new BadCredentialsException();
        }
    }

    public void validateAccessToken(String accessToken) {
        Claims claims = tokenParser.parse(accessToken);

        if (accessToken.equals("") || accessToken == null) {
            throw new BadCredentialsException();
        }

        if (claims.getExpiration().getTime() < System.currentTimeMillis()) {
            throw new BadCredentialsException();
        }
    }

    // refresh 요청을 했는데 진짜로 accessToken이 만료됐는지 확인
    public boolean isInValidRefresh(HttpServletRequest httpServletRequest) {
        String accessToken = httpServletRequest.getHeader("Authorization");
        Claims claims = tokenParser.parse(accessToken);

        if (claims.getExpiration().getTime() > System.currentTimeMillis()) {
            return true;
        }

        return false;
    }
    
}