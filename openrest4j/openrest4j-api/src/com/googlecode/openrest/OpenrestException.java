package com.googlecode.openrest;

public class OpenrestException extends Exception {
	public OpenrestException(int httpErrorCode, String error, String errorMessage) {
        super(error + ": " + errorMessage);
        this.httpErrorCode = httpErrorCode;
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public OpenrestException(int httpErrorCode, String error, String errorMessage, Throwable cause) {
        super(error + ": " + errorMessage, cause);
        this.httpErrorCode = httpErrorCode;
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public int httpErrorCode() {
        return httpErrorCode;
    }

    public String error() {
        return error;
    }

    public String errorMessage() {
        return errorMessage;
    }

    private final int httpErrorCode;
    private final String error;
    private final String errorMessage;
    
	private static final long serialVersionUID = 1L;
}
