package com.khpl.uzikbbang.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUp {
    private String name;
    private String email;
    private String passWord;

    @Builder
    public SignUp(String name, String email, String passWord) {
        this.name = name;
        this.email = email;
        this.passWord = passWord;
    }
}
