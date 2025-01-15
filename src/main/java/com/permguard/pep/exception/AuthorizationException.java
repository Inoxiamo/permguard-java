package com.permguard.pep.exception;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
