package com.khpl.uzikbbang.api.controller.auth.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SessionResponse {
    
    private final String accessToken;
}
