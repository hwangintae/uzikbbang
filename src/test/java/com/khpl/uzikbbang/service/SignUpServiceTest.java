package com.khpl.uzikbbang.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.SignUp;

@SpringBootTest
public class SignUpServiceTest {
    
    @Autowired
    private SignUpService signUpService;
    
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 가입 테스트")
    void testSave() {
        SignUp signUp = SignUp.builder()
            .name("황인태")
            .email("hwang@hwang.com")
            .passWord("1234")
        .build();

        UzikUser user = signUpService.save(signUp);

        assertEquals(1L, user.getId());
        assertEquals("황인태", user.getName());
        assertEquals("hwang@hwang.com", user.getEmail());
        assertEquals("1234", user.getPassWord());
    }
}
