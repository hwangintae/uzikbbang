package com.khpl.uzikbbang.exception;

import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException {
    
    public CustomException(String msg) {
        super(msg);
    }

    public abstract int getStatusCode();
}
