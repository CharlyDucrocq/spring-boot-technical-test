package com.supralog.recruitment.springexamusercrud.common;

import com.supralog.recruitment.springexamusercrud.common.error.BadAttributeException;

public interface DefaultDto {
    void validate() throws BadAttributeException;
}
