package com.jpmorgan.gbce.stockmarket.common.exception;

import com.jpmorgan.gbce.stockmarket.common.model.StatusCode;

/**
 * Class to hold the custom exception details
 */
public class RestRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 121888343460328214L;

    protected StatusCode errorStatusCode;

    public RestRuntimeException() {
        super();
    }

    public RestRuntimeException(String message) {
        super(message);
    }

    public RestRuntimeException(StatusCode errorStatusCode) {
        super(errorStatusCode.getMessage());
        this.errorStatusCode = errorStatusCode;
    }

    public RestRuntimeException(StatusCode errorStatusCode, String message) {
        super(message);
        this.errorStatusCode = errorStatusCode;
    }

    public StatusCode getErrorCode() {
        return errorStatusCode;
    }
}
