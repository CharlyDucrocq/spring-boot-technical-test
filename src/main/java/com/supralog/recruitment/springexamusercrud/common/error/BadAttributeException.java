package com.supralog.recruitment.springexamusercrud.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadAttributeException extends ResponseStatusException {
    public BadAttributeException(String msg){
        super(HttpStatus.BAD_REQUEST, msg);
    }
}
