package com.permguard.pep.exception;

public class MissingPermguardDataException extends RuntimeException {
    public MissingPermguardDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
