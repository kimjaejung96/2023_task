package com.tredlinx.task.common.exception;

import com.tredlinx.task.common.exception.model.enumurate.CustomApiCode;
import lombok.Getter;

@Getter
public class CustomException extends Exception{
    private final CustomApiCode customApiCode;

    public CustomException(CustomApiCode customApiCode) {
        this.customApiCode = customApiCode;
    }
}
