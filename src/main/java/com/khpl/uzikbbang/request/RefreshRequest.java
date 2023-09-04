package com.khpl.uzikbbang.request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.khpl.uzikbbang.config.TokenParser;
import com.khpl.uzikbbang.config.data.UserSession;
import com.khpl.uzikbbang.exception.BadCredentialsException;
import com.khpl.uzikbbang.exception.Unauthorized;

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

    public String getRefreshToken() {
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
            throw new Unauthorized();
        }
    }

    public void validateCookies(Cookie[] cookies) {
        if (cookies.length == 0 || cookies == null) {
            throw new Unauthorized();
        }
    }

    public void validateRefreshToken(String refreshToken) {
        if (refreshToken.equals("") || refreshToken == null) {
            throw new Unauthorized();
        }

        if (tokenParser.isExpiration(refreshToken)) {
            throw new Unauthorized();
        }
    }

    public void validateAccessToken(String accessToken) {
        if (accessToken.equals("") || accessToken == null) {
            throw new BadCredentialsException();
        }

        // refresh 요청을 했는데 진짜로 accessToken이 만료됐는지 확인
        if (tokenParser.isExpiration(accessToken) == false) {
            throw new BadCredentialsException();
        }
    }

    public Long getUserId() {
        String refreshToken = getRefreshToken();
        Claims claims = getClaims(refreshToken);
        UserSession userSession = tokenParser.getUserSession(claims);

        return userSession.getId();
    }
    
}
