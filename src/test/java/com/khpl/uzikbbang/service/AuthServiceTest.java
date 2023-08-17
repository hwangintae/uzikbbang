package com.khpl.uzikbbang.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.domain.Session;
import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.repository.SessionRepository;
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.SignIn;
import com.khpl.uzikbbang.request.SignUp;

@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private SessionRepository sessionRepository;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 가입 테스트")
    void testSignUp() {
        SignUp signUp = SignUp.builder()
            .name("황인태")
            .email("hwang@hwang.com")
            .passWord("1234")
        .build();

        UzikUser user = authService.signUp(signUp);

        assertEquals(1L, user.getId());
        assertEquals("황인태", user.getName());
        assertEquals("hwang@hwang.com", user.getEmail());
        assertEquals("1234", user.getPassWord());
    }

    @Test
    @DisplayName("로그인 후 세션 등록")
    @Transactional
    void testSignIn() {
        SignUp signUp = SignUp.builder()
            .name("황인태")
            .email("hwang@hwang.com")
            .passWord("1234")
        .build();

        UzikUser user = authService.signUp(signUp);

        SignIn signIn = SignIn.builder()
            .email("hwang@hwang.com")
            .passWord("1234")
        .build();

        authService.signIn(signIn);

        assertEquals(1, user.getSessions().size());
    }
}
