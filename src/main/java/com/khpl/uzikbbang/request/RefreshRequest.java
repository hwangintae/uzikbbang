package com.khpl.uzikbbang.request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.khpl.uzikbbang.config.TokenParser;
import com.khpl.uzikbbang.exception.BadCredentialsException;

import io.jsonwebtoken.Claims;

public class RefreshRequest {
    private final HttpServletRequest httpServletRequest;
    private final TokenParser tokenParser;

    public RefreshRequest(HttpServletRequest httpServletRequest, TokenParser tokenParser) {
        this.httpServletRequest = httpServletRequest;
        this.tokenParser = tokenParser;
    }

    public void valid() {
        validateRequest(this.httpServletRequest);

        Cookie[] cookies = this.httpServletRequest.getCookies();
        validateCookies(cookies);

        String refreshToken = cookies[0].getValue();
        validateRefreshToken(refreshToken);
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
    
}
