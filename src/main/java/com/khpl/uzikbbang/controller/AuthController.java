package com.khpl.uzikbbang.controller;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khpl.uzikbbang.domain.Session;
import com.khpl.uzikbbang.request.SignIn;
import com.khpl.uzikbbang.request.SignUp;
import com.khpl.uzikbbang.response.SessionResponse;
import com.khpl.uzikbbang.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;


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
    public SessionResponse singIn (@RequestBody SignIn signIn) {
        Session Session = authService.signIn(signIn);

        String accessToken = Session.getAccessToken();

        byte[] decode = Base64.getDecoder().decode(AUTH_KEY);

        SecretKey secretKey = Keys.hmacShaKeyFor(decode);

        String jws = Jwts.builder()
                .setSubject(accessToken)
                .signWith(secretKey)
                .setIssuedAt(new Date())
                .compact();
                
        return new SessionResponse(jws);
    }
    
}
