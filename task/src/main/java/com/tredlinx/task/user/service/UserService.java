package com.tredlinx.task.user.service;

import com.tredlinx.task.common.exception.CustomException;
import com.tredlinx.task.common.jwt.model.dto.JwtTokens;
import com.tredlinx.task.user.model.dto.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    void signup(User.signUp user) throws CustomException;

     JwtTokens signin(User.signIn user) throws CustomException;
}
