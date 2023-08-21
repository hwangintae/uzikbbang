package com.khpl.uzikbbang.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.khpl.uzikbbang.config.data.UserSession;
import com.khpl.uzikbbang.exception.Unauthorized;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class AuthResolver implements HandlerMethodArgumentResolver {

    @Value("${auth.key}")
    private String AUTH_KEY;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserSession.class);
    }

    @Override
    @Nullable
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

                String jws = webRequest.getHeader("Authorization");

                try {
                    Jws<Claims> claims = Jwts.parserBuilder()
                            .setSigningKey(Base64.getDecoder().decode(AUTH_KEY))
                            .build()
                            .parseClaimsJws(jws);
                    String userId = claims.getBody().getSubject();
                    return new UserSession(Long.parseLong(userId));
                } catch (JwtException e) {
                    throw new Unauthorized();
                }
    }
    
}
