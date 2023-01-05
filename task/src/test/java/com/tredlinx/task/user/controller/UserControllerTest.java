package com.tredlinx.task.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tredlinx.task.user.model.dto.User;
import com.tredlinx.task.user.model.entity.UserEntity;
import com.tredlinx.task.user.repository.UserRepo;
import com.tredlinx.task.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Autowired
    MockMvc mvc;

    @MockBean
    UserServiceImpl memberService;
    @MockBean
    UserRepo userRepo;

    @Test
    void signup() throws Exception {
        User.SignUp user = new User.SignUp("kjj", "pwtest", "김재중");
        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper.writeValueAsString(user);
        mvc.perform(post("/v1/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(data))
                .andExpect(status().isOk());

    }

    @Test
    void signin() {
    }

    @Test
    void profile() {
    }

    @Test
    void points() {
    }
}
