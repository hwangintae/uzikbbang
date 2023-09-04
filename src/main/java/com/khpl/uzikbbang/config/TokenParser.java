package com.khpl.uzikbbang.config;

import org.springframework.stereotype.Component;

import com.khpl.uzikbbang.config.data.UserSession;
import com.khpl.uzikbbang.exception.Unauthorized;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenParser {

    private final AppConfig appConfig;

    public Claims parse(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(appConfig.getAuthKey())
                    .build()
                    .parseClaimsJws(token);

            return claims.getBody();
        } catch (Exception e) {
            throw new Unauthorized();
        }
    }

    public boolean isExpiration(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(appConfig.getAuthKey())
                    .build()
                    .parseClaimsJws(token);

        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            throw new Unauthorized();
        }

        return false;
    }

    public UserSession getUserSession(Claims claims) {
        String userId = claims.getSubject();

        return new UserSession(Long.parseLong(userId));
    }
}
