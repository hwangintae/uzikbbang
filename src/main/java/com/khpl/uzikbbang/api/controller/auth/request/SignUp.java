package com.khpl.uzikbbang.api.controller.auth.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUp {
    private String name;
    private String email;
    private String password;

    @Builder
    public SignUp(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
