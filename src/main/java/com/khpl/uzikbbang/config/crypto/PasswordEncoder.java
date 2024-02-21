package com.khpl.uzikbbang.config.crypto;

import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(
        16
        , 8
        , 1
        , 32
        , 64
    );

    public String encrypt(String password) {
        return encoder.encode(password);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
