package com.tredlinx.task.user.controller;

import com.tredlinx.task.common.exception.CustomException;
import com.tredlinx.task.common.exception.model.dto.ResponseObject;
import com.tredlinx.task.common.exception.model.enumurate.ApiExceptionCode;
import com.tredlinx.task.user.model.dto.User;
import com.tredlinx.task.user.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "user", description = "User API")
@RestController
@RequestMapping("/v1")
public class UserController {
    private final UserServiceImpl userService;


    @Operation(summary = "signup", description = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @Parameters({
            @Parameter(name = "userId", description = "아이디", example = "kimjaejung96"),
            @Parameter(name = "userName", description = "이름", example = "김재중"),
            @Parameter(name = "pw", description = "비밀번호", example = "a14564kmdf")
    })
    @PostMapping("/signup")
    public ResponseEntity<ResponseObject> signup(@RequestBody User.signUp user) throws CustomException {
        ResponseObject responseObject = new ResponseObject(ApiExceptionCode.CREATED);
        userService.signup(user);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @Operation(summary = "signin", description = "로그인")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK"),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "401", description = "Unauthorized"),
//            @ApiResponse(responseCode = "403", description = "Forbidden"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
//    })
    @Parameters({
            @Parameter(name = "userId", description = "아이디", example = "kimjaejung96"),
            @Parameter(name = "pw", description = "비밀번호", example = "a14564kmdf")
    })
    @PostMapping("/signin")
    public ResponseEntity<ResponseObject> signin(@RequestBody User.signIn user) throws CustomException {
        ResponseObject responseObject = new ResponseObject(ApiExceptionCode.OK);
        responseObject.setBody(userService.signin(user));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
