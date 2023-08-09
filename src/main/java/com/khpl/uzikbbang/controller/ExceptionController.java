package com.khpl.uzikbbang.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khpl.uzikbbang.exception.CustomException;
import com.khpl.uzikbbang.response.ErrorResponse;

@ControllerAdvice
public class ExceptionController {
    
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> CustomException(CustomException e) {
        int code = e.getStatusCode();
        
        ErrorResponse body = ErrorResponse.builder()
            .code(String.valueOf(code))
            .msg(e.getMessage())
        .build();

        return ResponseEntity.status(code).body(body);
    }
}
