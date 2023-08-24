package com.khpl.uzikbbang.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khpl.uzikbbang.request.SignIn;
import com.khpl.uzikbbang.request.SignUp;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @DisplayName(value = "회원가입")
    void testSignUp() throws JsonProcessingException, Exception {
        SignUp signUp = SignUp.builder()
            .email("hwang@hwang.com")
            .password("1234")
            .name("황인태")
        .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUp)))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "로그인")
    void testSingIn() throws JsonProcessingException, Exception {
        testSignUp();

        SignIn signIn = SignIn.builder()
            .email("hwang@hwang.com")
            .password("1234")
        .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signIn)))
            .andExpect(status().isOk())
            .andExpect(cookie().exists("accessToken"))
            .andDo(print());
    }

    @Test
    @DisplayName(value = "foo에 요청을 했을 경우 cookie로 jwt를 보내야 한다.")
    void testFoo() throws Exception {
        Cookie cookie = new Cookie("accessToken", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjkyNjczMDMzfQ.-bFTSDafjnqW9A4CKejZTz5RP15p-aIG0IgD8-jsN8Y");
        
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/foo")
            .cookie(cookie))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
