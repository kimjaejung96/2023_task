package com.tredlinx.task.user.service.impl;

import com.tredlinx.task.common.component.JwtGenerator;
import com.tredlinx.task.common.exception.CustomException;
import com.tredlinx.task.common.exception.model.enumurate.ApiExceptionCode;
import com.tredlinx.task.common.jwt.model.dto.JwtTokens;
import com.tredlinx.task.user.model.dto.User;
import com.tredlinx.task.user.model.entity.UserEntity;
import com.tredlinx.task.user.repository.UserRepo;
import com.tredlinx.task.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final JwtGenerator jwtGenerator;
    @Override
    @Transactional
    public void signup(User.signUp user) throws CustomException {
        if (userRepo.existsByUserId(user.getUserId())) {
            throw new CustomException(ApiExceptionCode.ID_ALREADY_EXIST);
        }
        UserEntity userEntity = UserEntity.createUser(user);
        userRepo.save(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public JwtTokens signin(User.signIn user) throws CustomException {
        UserEntity userEntity = userRepo.findByUserId(user.getUserId()).orElseThrow(() -> new CustomException(ApiExceptionCode.USER_NOT_FOUND));
        userEntity.checkPw(user.getPw());
        return jwtGenerator.createJwtToken(userEntity.getUid());
    }
}
