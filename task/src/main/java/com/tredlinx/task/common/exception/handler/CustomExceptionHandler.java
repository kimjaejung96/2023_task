package com.tredlinx.task.common.exception.handler;

import com.tredlinx.task.common.exception.CustomException;
import com.tredlinx.task.common.exception.CustomRuntimeException;
import com.tredlinx.task.common.exception.model.dto.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ResponseObject> handleCustomException(CustomException ex) {
        ResponseObject responseObject = new ResponseObject(ex.getCustomApiCode());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @ExceptionHandler({CustomRuntimeException.class})
    public ResponseEntity<ResponseObject> handleCustomRuntimeException(CustomRuntimeException ex) {
        ResponseObject responseObject = new ResponseObject(ex.getCustomApiCode());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

}
