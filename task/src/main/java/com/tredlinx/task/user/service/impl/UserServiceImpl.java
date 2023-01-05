package com.tredlinx.task.user.service.impl;

import com.tredlinx.task.common.component.JwtUtils;
import com.tredlinx.task.common.exception.CustomRuntimeException;
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
    public void signup(User.SignUp user) {
        if (userRepo.existsByUserId(user.getUserid())) {
            throw new CustomRuntimeException(CustomApiCode.ID_ALREADY_EXIST);
        }
        UserEntity userEntity = UserEntity.createUser(user);
        userRepo.save(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public JwtTokens signin(User.SignIn user) {
        UserEntity userEntity = userRepo.findByUserId(user.getUserid()).orElseThrow(() -> new CustomRuntimeException(CustomApiCode.USER_NOT_FOUND));
        userEntity.checkPw(user.getPw());
        return jwtUtils.createJwtToken(userEntity.getUid());
    }

    @Override
    @Transactional(readOnly = true)
    public User.Profile profile() {
        UserEntity userEntity = userRepo.findByUid(JwtUtils.getUid()).orElseThrow(() -> new CustomRuntimeException(CustomApiCode.INCORRECT_JWT_TOKEN));
        return new User.Profile(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> points() {
        UserEntity userEntity = userRepo.findByUid(JwtUtils.getUid()).orElseThrow(() -> new CustomRuntimeException(CustomApiCode.INCORRECT_JWT_TOKEN));
        return Map.of("point", userEntity.getPoint());
    }
}
