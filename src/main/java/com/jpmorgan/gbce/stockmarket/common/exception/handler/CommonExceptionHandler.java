package com.jpmorgan.gbce.stockmarket.common.exception.handler;

import com.jpmorgan.gbce.stockmarket.common.exception.RestRuntimeException;
import com.jpmorgan.gbce.stockmarket.common.model.ApiStatusCode;
import com.jpmorgan.gbce.stockmarket.common.model.RestApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Class to handle the exceptions at centralized location
 */
@ControllerAdvice
public class CommonExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    /**
     * Method to handle the rest runtime exceptions
     *
     * @param ex
     * @param req
     * @return
     * @throws IOException
     */
    @ExceptionHandler(RestRuntimeException.class)
    public ResponseEntity<RestApiResponse<String>> handleRestException(RestRuntimeException ex, HttpServletRequest req) throws IOException {

        log.error("RestRuntimeException in gbce stock market service : code {} , message {}, exception {}", ex.getErrorCode(), ex.getMessage(), ex);
        RestApiResponse<String> errorResponse = new RestApiResponse<String>(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<RestApiResponse<String>>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatusCode()));
    }

    /**
     * Method to handle the all other exceptions
     *
     * @param ex
     * @param req
     * @return
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestApiResponse<String>> handleException(Exception ex, HttpServletRequest req) throws IOException {

        log.error("Exception in gbce stock market service : message {}, exception {}", ex.getMessage(), ex);
        RestApiResponse<String> errorResponse = new RestApiResponse<String>(ApiStatusCode.INTERNAL_SERVER_ERROR,
                (ex.getCause() != null) ? ex.getCause().getMessage() : ex.getMessage());
        return new ResponseEntity<RestApiResponse<String>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Method to handle the MissingServletRequestParameterException
     *
     * @param ex
     * @param req
     * @return
     * @throws IOException
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RestApiResponse<String>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest req) throws IOException {

        log.error("MissingServletRequestParameterException in gbce stock market service : message {}, exception {}", ex.getMessage(), ex);
        RestApiResponse<String> errorResponse = new RestApiResponse<String>(ApiStatusCode.BAD_REQUEST,
                (ex.getCause() != null) ? ex.getCause().getMessage() : ex.getMessage());
        return new ResponseEntity<RestApiResponse<String>>(errorResponse, HttpStatus.BAD_REQUEST);

    }

}
