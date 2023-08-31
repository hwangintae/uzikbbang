package com.khpl.uzikbbang.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.BeforeEach;
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
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.SignIn;
import com.khpl.uzikbbang.request.SignUp;
import com.khpl.uzikbbang.service.AuthService;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }
    
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
        SignUp signUp = SignUp.builder()
            .email("hwang@hwang.com")
            .password("1234")
            .name("황인태")
        .build();

        authService.signUp(signUp);

        SignIn signIn = SignIn.builder()
            .email("hwang@hwang.com")
            .password("1234")
        .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signIn)))
            .andExpect(status().isOk())
            .andExpect(cookie().exists("refreshToken"))
            .andDo(print());
    }

    @Test
    @DisplayName(value = "Authorization 토큰 테스트")
    void testAuthorization() throws Exception {
        String authorization = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjkzNDUzODAwLCJleHAiOjE2OTM0NTQ0MDB9.4_T23qzFXORoh43Dr99P3YCXjGVkFY2yx2RXUgrL-i4";

        mockMvc.perform(MockMvcRequestBuilders.get("/auth/foo")
            .header("authorization", authorization))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName(value = "Authorization 토큰 만료 시 재발급 테스트")
    void testNonAuthorization() throws Exception {
        String authorization = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjkyOTU3MjUzLCJleHAiOjE2OTI5NTc4NTN9._dGdzf2LgngRleyQc_XE0mi8a43Sk1amJ8VH-w_lghI";

        mockMvc.perform(MockMvcRequestBuilders.get("/auth/foo")
            .header("authorization", authorization))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());

        SignUp signUp = SignUp.builder()
            .email("hwang@hwang.com")
            .password("1234")
            .name("황인태")
        .build();

        authService.signUp(signUp);

        SignIn signIn = SignIn.builder()
            .email("hwang@hwang.com")
            .password("1234")
        .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signIn)))
            .andExpect(status().isOk())
            .andExpect(cookie().exists("refreshToken"))
            .andDo(print());
    }

    @Test
    @DisplayName(value = "accessToken가 만료되었을 때, refreshToken으로 accessToken을 갱신한다.")
    void testFoo() throws Exception {
        Cookie cookie = new Cookie("refreshToken", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjkzNDU0MTMwLCJleHAiOjE2OTQwNTg5Mjl9.zsPxXE81SpzUGbLIpuswme9BShybZUK9U_5k1hPpXnQ");
        
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/refresh")
            .cookie(cookie)
            .header("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjkyOTU3MjUzLCJleHAiOjE2OTI5NTc4NTN9._dGdzf2LgngRleyQc_XE0mi8a43Sk1amJ8VH-w_lghI"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(print());
    }
}
