package com.khpl.uzikbbang.exception;

public class BlockUserException extends CustomException {

    private static final String MSG = "차단된 사용자 입니다.";

    public BlockUserException() {
        super(MSG);
    }

    @Override
    public int getStatusCode() {
        return 10_000;
    }
    
}
