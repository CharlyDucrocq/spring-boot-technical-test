package com.supralog.recruitment.springexamusercrud.common.error;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
class MethodeArgumentNotValidHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ErrorOutput handleValidationExceptions(MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        for (var e : ex.getBindingResult().getAllErrors()){
            errors.put(e.getObjectName(), e.getDefaultMessage());
        }
        return new ErrorOutput(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getBindingResult().getObjectName(),
                errors
        );
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter @Setter
    private static class ErrorOutput {
        private int status;
        private String error;
        private String model;
        private Map<String, String> errors;
    }
}