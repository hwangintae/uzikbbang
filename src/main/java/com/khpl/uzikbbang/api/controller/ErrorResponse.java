package com.khpl.uzikbbang.api.controller;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String code;
    private final String msg;

    @Builder
    public ErrorResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
