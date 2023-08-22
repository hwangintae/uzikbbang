package com.khpl.uzikbbang.config;

import java.util.Base64;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;

@Getter
@ConfigurationProperties(prefix =  "uzik")
public class AppConfig {
    private byte[] authKey;

    public void setAuthKey(String authKey) {
        this.authKey = Base64.getDecoder().decode(authKey);
    }
}
