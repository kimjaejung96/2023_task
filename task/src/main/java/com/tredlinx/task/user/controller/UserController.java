package com.tredlinx.task.user.controller;

import com.tredlinx.task.user.model.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
