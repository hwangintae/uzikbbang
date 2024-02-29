package com.khpl.uzikbbang.api.controller.auth.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignOut {
    private String email;
    private String password;

    @Builder
    public SignOut(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
}
