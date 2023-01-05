package com.tredlinx.task.user.service;

import com.tredlinx.task.common.exception.CustomException;
import com.tredlinx.task.common.jwt.model.dto.JwtTokens;
import com.tredlinx.task.user.model.dto.User;

import java.util.Map;

public interface UserService {
    void signup(User.signUp user) throws CustomException;

     JwtTokens signin(User.signIn user) throws CustomException;

    User.profile profile() throws CustomException;

    Map<String, Long> points() throws CustomException;
}
