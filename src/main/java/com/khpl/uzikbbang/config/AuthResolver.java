package com.khpl.uzikbbang.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthResolver implements HandlerMethodArgumentResolver {

    private final AppConfig appConfig;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserSession.class);
    }

    @Override
    @Nullable
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

                HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
                if (httpServletRequest == null) {
                    throw new Unauthorized();
                }

                // TODO 쿠키 인증 절차 추가로 필요함
                Cookie[] cookies = httpServletRequest.getCookies();
                if (cookies.length == 0) {
                    throw new Unauthorized();
                }

                String accessToken = cookies[0].getValue();

                try {
                    Jws<Claims> claims = Jwts.parserBuilder()
                            .setSigningKey(appConfig.getAuthKey())
                            .build()
                            .parseClaimsJws(accessToken);
                    String userId = claims.getBody().getSubject();
                    return new UserSession(Long.parseLong(userId));
                } catch (Exception e) {
                    throw new Unauthorized();
                }
    }
    
}
