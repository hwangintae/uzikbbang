package com.khpl.uzikbbang.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
