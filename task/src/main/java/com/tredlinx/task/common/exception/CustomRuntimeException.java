package com.tredlinx.task.common.exception;

import com.tredlinx.task.common.exception.model.enumurate.ApiExceptionCode;

public class CustomRuntimeException extends RuntimeException{
    private final ApiExceptionCode apiExceptionCode;

    public CustomRuntimeException(ApiExceptionCode apiExceptionCode) {
        this.apiExceptionCode = apiExceptionCode;
    }
}
