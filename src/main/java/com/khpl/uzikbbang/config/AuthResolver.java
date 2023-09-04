package com.khpl.uzikbbang.config;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.khpl.uzikbbang.config.data.UserSession;
import com.khpl.uzikbbang.exception.TokenExpirateException;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AuthResolver implements HandlerMethodArgumentResolver {

    private final TokenParser tokenParser;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserSession.class);
    }

    @Override
    @Nullable
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

        String accessToken = webRequest.getHeader("Authorization");

        if (tokenParser.isExpiration(accessToken)) {
            throw new TokenExpirateException();
        }
        
        Claims claims = tokenParser.parse(accessToken);
        UserSession userSession = tokenParser.getUserSession(claims);

        log.info("Authorization : userId = {}", userSession.getId());

        return userSession;
    }
    
}
