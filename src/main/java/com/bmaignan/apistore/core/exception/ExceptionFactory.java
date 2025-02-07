package com.bmaignan.apistore.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public final class ExceptionFactory {
    private ExceptionFactory() {
    }

    private static ErrorResponseException buildException(HttpStatus status, String detail, Throwable cause) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        return new ErrorResponseException(status, problemDetail, cause);
    }

    public static ErrorResponseException notFound(String detail) {
        return buildException(HttpStatus.NOT_FOUND, detail, null);
    }
}
