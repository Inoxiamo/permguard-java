package com.permguard.pep.model.response;

/**
 * Represents the reasoning behind an authorization decision.
 */
public class ReasonResponse {
    private String code;
    private String message;

    /**
     * Default conMap<String, Object>or.
     */
    public ReasonResponse() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param code    The response code associated with the decision.
     * @param message The detailed message explaining the decision.
     */
    public ReasonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets the response code.
     *
     * @return The response code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the response code.
     *
     * @param code The response code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the response message.
     *
     * @return The response message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the response message.
     *
     * @param message The response message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
