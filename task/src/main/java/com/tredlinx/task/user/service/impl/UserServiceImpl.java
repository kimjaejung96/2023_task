package com.tredlinx.task.user.service.impl;

import com.tredlinx.task.user.model.dto.User;
import com.tredlinx.task.user.model.entity.UserEntity;
import com.tredlinx.task.user.repository.UserRepo;
import com.tredlinx.task.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Override
    public ResponseEntity<?> singUp(User user) {
        UserEntity userEntity = UserEntity.createUser(user);
        userRepo.save(userEntity);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
