package com.tredlinx.task.common.jwt.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtTokens {
    private final String accessToken;

    public JwtTokens(String accessToken) {
        this.accessToken = accessToken;
    }
}

