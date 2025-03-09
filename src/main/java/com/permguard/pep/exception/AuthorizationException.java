package com.permguard.pep.exception;

/**
 * Exception thrown when an authorization request fails.
 */
public class AuthorizationException extends RuntimeException {

    /**
     * Constructs an AuthorizationException with a specific message.
     *
     * @param message The error message describing the failure.
     */
    public AuthorizationException(String message) {
        super(message);
    }

    /**
     * Constructs an AuthorizationException with a message and cause.
     *
     * @param message The error message describing the failure.
     * @param cause   The root cause of the exception.
     */
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
