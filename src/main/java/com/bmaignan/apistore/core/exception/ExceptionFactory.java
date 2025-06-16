package com.bmaignan.apistore.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.util.logging.Logger;

public final class ExceptionFactory {
    private static final Logger LOGGER = Logger.getLogger(ExceptionFactory.class.getName());
    private ExceptionFactory() {
    }

    private static ErrorResponseException buildException(HttpStatus status, String detail, Throwable cause) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        LOGGER.severe(problemDetail.toString());
        return new ErrorResponseException(status, problemDetail, cause);
    }

    public static ErrorResponseException notFound(String detail) {
        return buildException(HttpStatus.NOT_FOUND, detail, null);
    }

    public static ErrorResponseException alreadyExists(String detail) {
        return buildException(HttpStatus.CONFLICT, detail, null);
    }

    public static ErrorResponseException conflict(String detail) {
        return buildException(HttpStatus.CONFLICT, detail, null);
    }

    public static ErrorResponseException outOfStock() {
        return buildException(HttpStatus.CONFLICT, "This article is out of stock", null);
    }
}
