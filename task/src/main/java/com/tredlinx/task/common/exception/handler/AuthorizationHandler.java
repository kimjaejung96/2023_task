package com.tredlinx.task.common.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tredlinx.task.common.exception.model.dto.ResponseObject;
import com.tredlinx.task.common.exception.model.enumurate.CustomApiCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
@Slf4j
public class AuthorizationHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    public AuthorizationHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("Forbidden : " + accessDeniedException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        try (OutputStream os = response.getOutputStream()) {
            objectMapper.writeValue(os, new ResponseObject(CustomApiCode.FORBIDDEN));
            os.flush();
        }
    }
}
