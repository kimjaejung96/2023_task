package com.tredlinx.task.user.controller;

import com.tredlinx.task.common.exception.CustomException;
import com.tredlinx.task.common.exception.model.dto.ResponseObject;
import com.tredlinx.task.common.exception.model.enumurate.CustomApiCode;
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
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Tag(name = "user", description = "User API")
@RestController
@RequestMapping("/v1")
public class UserController {
    private final UserServiceImpl userService;


    @Operation(summary = "signup", description = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR"),
            @ApiResponse(responseCode = "602", description = "해당 아이디가 존재합니다."),
            @ApiResponse(responseCode = "999", description = "SYSTEM_ERROR"),
    })
    @Parameters({
            @Parameter(name = "userId", description = "아이디", example = "kimjaejung96"),
            @Parameter(name = "userName", description = "이름", example = "김재중"),
            @Parameter(name = "pw", description = "비밀번호", example = "a14564kmdf")
    })
    @PostMapping("/signup")
    public ResponseEntity<ResponseObject> signup(@RequestBody User.signUp user) throws CustomException {
        ResponseObject responseObject = new ResponseObject(CustomApiCode.CREATED);
        userService.signup(user);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @Operation(summary = "signin", description = "로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "600", description = "유저를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "601", description = "비밀번호가 올바르지 않습니다."),
            @ApiResponse(responseCode = "999", description = "SYSTEM_ERROR"),
    })
    @Parameters({
            @Parameter(name = "userId", description = "아이디", example = "kimjaejung96"),
            @Parameter(name = "pw", description = "비밀번호", example = "a14564kmdf")
    })
    @PostMapping("/signin")
    public ResponseEntity<ResponseObject> signin(@RequestBody User.signIn user) throws CustomException {
        ResponseObject responseObject = new ResponseObject(CustomApiCode.OK);
        responseObject.setBody(userService.signin(user));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @Operation(summary = "profile", description = "프로필 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "998", description = "Jwt 토큰이 올바르지 않습니다."),
            @ApiResponse(responseCode = "999", description = "SYSTEM_ERROR"),
    })
    @GetMapping("/profile")
    public ResponseEntity<ResponseObject> profile() throws CustomException {
        ResponseObject responseObject = new ResponseObject(CustomApiCode.OK);
        responseObject.setBody(userService.profile());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @Operation(summary = "points", description = "포인트 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "998", description = "Jwt 토큰이 올바르지 않습니다."),
            @ApiResponse(responseCode = "999", description = "SYSTEM_ERROR"),
    })
    @GetMapping("/points")
    public ResponseEntity<ResponseObject> points() throws CustomException {
        ResponseObject responseObject = new ResponseObject(CustomApiCode.OK);
        responseObject.setBody(userService.points());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
