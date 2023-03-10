package com.tredlinx.task.common.exception.model.enumurate;

import lombok.Getter;

@Getter
public enum CustomApiCode {
    OK(200, "OK"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    USER_NOT_FOUND(600, "유저를 찾을 수 없습니다."),
    INVALID_PASSWORD(601, "비밀번호가 올바르지 않습니다."),
    ID_ALREADY_EXIST(602, "해당 아이디가 존재합니다."),
    ARTICLE_NOT_FOUND(603, "글을 찾을 수 없습니다."),
    ARTICLE_UNAUTHORIZED(604, "글 권한이 없습니다."),
    COMMENT_NOT_FOUND(605, "댓글을 찾을 수 없습니다."),
    COMMENT_UNAUTHORIZED(606, "댓글 권한이 없습니다."),
    INCORRECT_JWT_TOKEN(998, "Jwt 토큰이 올바르지 않습니다."),
    SYSTEM_ERROR(999, "SYSTEM_ERROR");
    private final int apiCode;
    private final String message;

    CustomApiCode(int code, String message) {
        this.apiCode = code;
        this.message = message;
    }
}
