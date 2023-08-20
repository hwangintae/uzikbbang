package com.khpl.uzikbbang.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignIn {
    private String email;
    private String passWord;

    @Builder
    public SignIn(String email, String passWord) {
        this.email = email;
        this.passWord = passWord;
    }
}
