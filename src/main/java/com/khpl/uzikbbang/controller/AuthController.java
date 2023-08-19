package com.khpl.uzikbbang.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khpl.uzikbbang.domain.Session;
import com.khpl.uzikbbang.request.SignIn;
import com.khpl.uzikbbang.request.SignUp;
import com.khpl.uzikbbang.service.AuthService;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Value("$")
    private String sessionKey;
    
    @PostMapping(value = "/signup")
    public void signUp(SignUp signUp) {
        authService.signUp(signUp);
    }

    @PostMapping(value = "/signin")
    public void singIn (@RequestBody SignIn signIn) {
        Session Session = authService.signIn(signIn);

        String accessToken = Session.getAccessToken();

        String jws = Jwts.builder()
                .setSubject(accessToken)
                .signWith(key)
                .setIssuedAt(new Date())
                .compact();
    }
    
}
