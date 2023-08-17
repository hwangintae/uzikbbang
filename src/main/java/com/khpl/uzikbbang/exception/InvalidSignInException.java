package com.khpl.uzikbbang.exception;

public class InvalidSignInException extends CustomException {

    private static final String MSG = "이메일과 비밀번호를 확인해 주세요.";

    public InvalidSignInException() {
        super(MSG);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
