package com.khpl.uzikbbang.exception;

public class AlreadySignUpEmailException extends CustomException {

    private static final String MSG = "이미 가입된 이메일 입니다.";

    public AlreadySignUpEmailException() {
        super(MSG);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
