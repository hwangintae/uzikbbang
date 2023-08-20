package com.khpl.uzikbbang.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.SignIn;
import com.khpl.uzikbbang.request.SignUp;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthController authController;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }
    
    @Test
    @DisplayName(value = "회원가입")
    void testSignUp() throws JsonProcessingException, Exception {
        SignUp build = SignUp.builder()
            .email("hwang@hwang.com")
            .passWord("1234")
            .name("황인태")
        .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(build)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSingIn() throws JsonProcessingException, Exception {
        SignUp signUp = SignUp.builder()
            .email("hwang@hwang.com")
            .passWord("1234")
            .name("황인태")
        .build();

        authController.signUp(signUp);

        SignIn signIn = SignIn.builder()
            .email("hwang@hwang.com")
            .passWord("1234")
        .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signIn)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        String string = mvcResult.getResponse().getContentAsString();

        System.out.println(string);
    }
}
