package com.tredlinx.task.user.service.impl;

import com.tredlinx.task.common.component.JwtUtils;
import com.tredlinx.task.common.exception.CustomException;
import com.tredlinx.task.common.exception.model.enumurate.CustomApiCode;
import com.tredlinx.task.common.jwt.model.dto.JwtTokens;
import com.tredlinx.task.user.model.dto.User;
import com.tredlinx.task.user.model.entity.UserEntity;
import com.tredlinx.task.user.repository.UserRepo;
import com.tredlinx.task.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final JwtUtils jwtUtils;
    @Override
    @Transactional
    public void signup(User.signUp user) throws CustomException {
        if (userRepo.existsByUserId(user.getUserid())) {
            throw new CustomException(CustomApiCode.ID_ALREADY_EXIST);
        }
        UserEntity userEntity = UserEntity.createUser(user);
        userRepo.save(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public JwtTokens signin(User.signIn user) throws CustomException {
        UserEntity userEntity = userRepo.findByUserId(user.getUserid()).orElseThrow(() -> new CustomException(CustomApiCode.USER_NOT_FOUND));
        userEntity.checkPw(user.getPw());
        return jwtUtils.createJwtToken(userEntity.getUid());
    }

    @Override
    @Transactional(readOnly = true)
    public User.profile profile() throws CustomException {
        UserEntity userEntity = userRepo.findByUid(JwtUtils.getUid()).orElseThrow(() -> new CustomException(CustomApiCode.INCORRECT_JWT_TOKEN));
        return new User.profile(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> points() throws CustomException {
        UserEntity userEntity = userRepo.findByUid(JwtUtils.getUid()).orElseThrow(() -> new CustomException(CustomApiCode.INCORRECT_JWT_TOKEN));
        return Map.of("point", userEntity.getPoint());
    }
}
