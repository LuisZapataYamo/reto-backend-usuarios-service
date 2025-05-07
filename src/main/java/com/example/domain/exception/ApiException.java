package com.example.domain.exception;

import com.example.domain.constants.HttpCodesConstants;
import lombok.Getter;

@Getter
public abstract class ApiException extends RuntimeException {
    private final String errorCode;
    private final HttpCodesConstants httpStatus;

    protected ApiException(String message, String errorCode, HttpCodesConstants httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

}
