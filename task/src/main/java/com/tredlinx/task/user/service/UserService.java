package com.tredlinx.task.user.service;

import com.tredlinx.task.user.model.dto.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> singUp(User user);
}
