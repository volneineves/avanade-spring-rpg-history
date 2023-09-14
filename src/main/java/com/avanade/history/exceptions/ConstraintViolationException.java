package com.avanade.history.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class ConstraintViolationException extends DataIntegrityViolationException {

    public ConstraintViolationException(String message) {
        super(message);
    }
}
