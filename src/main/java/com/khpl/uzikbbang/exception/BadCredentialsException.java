package com.khpl.uzikbbang.exception;

public class BadCredentialsException extends CustomException {

    private static final String MSG = "자격 증명에 실패하였습니다.";

    public BadCredentialsException() {
        super(MSG);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
    
}
