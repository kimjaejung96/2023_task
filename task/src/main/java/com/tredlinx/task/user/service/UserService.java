package com.tredlinx.task.user.service;

import com.tredlinx.task.common.jwt.model.dto.JwtTokens;
import com.tredlinx.task.user.model.dto.User;

import java.util.Map;

public interface UserService {
    void signup(User.SignUp user) ;

     JwtTokens signin(User.SignIn user) ;

    User.Profile profile() ;

    Map<String, Long> points() ;
}
