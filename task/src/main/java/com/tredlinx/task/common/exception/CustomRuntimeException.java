package com.tredlinx.task.common.exception;

import com.tredlinx.task.common.exception.model.enumurate.CustomApiCode;

public class CustomRuntimeException extends RuntimeException{
    private final CustomApiCode customApiCode;

    public CustomRuntimeException(CustomApiCode customApiCode) {
        this.customApiCode = customApiCode;
    }
}
