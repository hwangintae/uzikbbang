package com.khpl.uzikbbang.exception;

public class TokenExpirateException extends CustomException {

    private static final String MSG = "토큰이 만료되었습니다.";

    public TokenExpirateException() {
        super(MSG);
    }

    @Override
    public int getStatusCode() {
        return 10_001;
    }
    
}
