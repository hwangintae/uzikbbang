package com.khpl.uzikbbang.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignIn {
    private String email;
    private String passWord;
}
