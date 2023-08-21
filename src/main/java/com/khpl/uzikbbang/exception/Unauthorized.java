package com.khpl.uzikbbang.exception;

public class Unauthorized extends CustomException {

    private static final String MSG = "인증이 필요합니다.";

    public Unauthorized() {
        super(MSG);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
    
}
