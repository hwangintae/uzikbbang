package com.khpl.uzikbbang.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SessionResponse {
    
    private final String accessToken;
}
