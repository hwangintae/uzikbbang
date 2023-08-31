package com.khpl.uzikbbang.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.crypto.PasswordEncoder;
import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.exception.InvalidSignInException;
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.SignIn;
import com.khpl.uzikbbang.request.SignUp;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

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
            .password("1234")
        .build();

        UzikUser user = authService.signUp(signUp);

        assertEquals(1L, user.getId());
        assertEquals("황인태", user.getName());
        assertEquals("hwang@hwang.com", user.getEmail());
        assertTrue(passwordEncoder.matches("1234", user.getPassword()));
    }

    @Test
    @DisplayName("회원 가입 시 password가 암호화 된다")
    void testSignUpScrypt() {
        SignUp signUp = SignUp.builder()
            .name("황인태")
            .email("hwang@hwang.com")
            .password("1234")
        .build();

        UzikUser user = authService.signUp(signUp);

        assertEquals(1L, user.getId());
        assertEquals("황인태", user.getName());
        assertEquals("hwang@hwang.com", user.getEmail());
        assertNotEquals("1234", user.getPassword());
    }

    @Test
    @DisplayName("로그인 후 세션 등록")
    void testSignIn() {
        SignUp signUp = SignUp.builder()
            .name("황인태")
            .email("hwang@hwang.com")
            .password("1234")
        .build();

        UzikUser user = authService.signUp(signUp);

        SignIn signIn = SignIn.builder()
            .email("hwang@hwang.com")
            .password("1234")
        .build();

        authService.signIn(signIn);

        assertEquals(1, user.getSessions().size());
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    void testNotSignIn() {
        SignUp signUp = SignUp.builder()
            .name("황인태")
            .email("hwang@hwang.com")
            .password("1234")
        .build();

        authService.signUp(signUp);

        SignIn signIn = SignIn.builder()
            .email("hwang@hwang.com")
            .password("4321")
        .build();

        assertThrows(InvalidSignInException.class, () -> authService.signIn(signIn));
    }


    @Test
    @DisplayName("jwt key")
    void testJwtKey() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        
        byte[] encoded = key.getEncoded();

        String strKey = Base64.getEncoder().encodeToString(encoded);

        System.out.println(strKey);
    }

    @Test
    @DisplayName("사용자 사용 여부 업데이트")
    void testUseAt() {
        SignUp signUp = SignUp.builder()
            .name("황인태")
            .email("hwang@hwang.com")
            .password("1234")
        .build();

        UzikUser user = authService.signUp(signUp);
        assertTrue(user.isUseAt());

        userService.updateTest(user.getId(), false);
    }
}
