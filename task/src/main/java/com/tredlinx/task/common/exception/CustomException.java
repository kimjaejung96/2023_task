package com.tredlinx.task.common.exception;

import com.tredlinx.task.common.exception.model.enumurate.ApiExceptionCode;
import lombok.Getter;

@Getter
public class CustomException extends Exception{
    private final ApiExceptionCode apiExceptionCode;

    public CustomException(ApiExceptionCode apiExceptionCode) {
        this.apiExceptionCode = apiExceptionCode;
    }
}
