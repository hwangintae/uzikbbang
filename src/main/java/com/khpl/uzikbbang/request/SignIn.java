package com.khpl.uzikbbang.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignIn {
    private String email;
    private String password;

    @Builder
    public SignIn(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
