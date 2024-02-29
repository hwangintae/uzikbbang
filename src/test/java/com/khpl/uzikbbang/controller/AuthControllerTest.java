package com.khpl.uzikbbang.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khpl.uzikbbang.api.controller.auth.request.SignIn;
import com.khpl.uzikbbang.api.controller.auth.request.SignUp;
import com.khpl.uzikbbang.api.service.auth.AuthService;
import com.khpl.uzikbbang.domain.user.UserRepository;

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

//     @Autowired
//     private TokenParser tokenParser;

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
    @DisplayName(value = "Authorization accessToken 테스트")
    void testAuthorization() throws Exception {

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

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signIn)))
                .andExpect(status().isOk())
                .andExpect(cookie().exists("refreshToken"))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(content);

        mockMvc.perform(MockMvcRequestBuilders.get("/auth/foo")
                .header("authorization", jsonObject.get("accessToken")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Authorization accessToken 만료 테스트")
    void testNonAuthorization() throws Exception {

        // 만료된 accessToken
        String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjkyOTU3MjUzLCJleHAiOjE2OTI5NTc4NTN9._dGdzf2LgngRleyQc_XE0mi8a43Sk1amJ8VH-w_lghI";

        mockMvc.perform(MockMvcRequestBuilders.get("/auth/foo")
                .header("authorization", accessToken))
                .andExpect(status().is(10_001))
                .andDo(print());
    }

    @Test
    @DisplayName(value = "accessToken 만료 시 재발급 테스트")
    void testRefresh() throws Exception {
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

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signIn)))
                .andExpect(status().isOk())
                .andExpect(cookie().exists("refreshToken"))
                .andReturn();

        // 만료된 accessToken
        String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjkyOTU3MjUzLCJleHAiOjE2OTI5NTc4NTN9._dGdzf2LgngRleyQc_XE0mi8a43Sk1amJ8VH-w_lghI";
        
        Cookie cookie = result.getResponse().getCookie("refreshToken");

        if (cookie == null)
            return;
        
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/refresh")
                .cookie(cookie)
                .header("Authorization", accessToken))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName(value = "accessToken이 만료가 안됐지만 refresh를 요청한 경우")
    void testNonRefresh() throws Exception {
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

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signIn)))
                .andExpect(status().isOk())
                .andExpect(cookie().exists("refreshToken"))
                .andReturn();
                        
        Cookie cookie = result.getResponse().getCookie("refreshToken");

        if (cookie == null)
            return;

        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/refresh")
                .cookie(cookie)
                .header("Authorization", jsonObject.get("accessToken")))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}
