package com.khpl.uzikbbang.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.api.controller.auth.Session;
import com.khpl.uzikbbang.api.controller.auth.request.SignIn;
import com.khpl.uzikbbang.api.controller.auth.request.SignOut;
import com.khpl.uzikbbang.api.controller.auth.request.SignUp;
import com.khpl.uzikbbang.api.service.auth.AuthService;
import com.khpl.uzikbbang.api.service.auth.UserService;
import com.khpl.uzikbbang.config.crypto.PasswordEncoder;
import com.khpl.uzikbbang.domain.Level;
import com.khpl.uzikbbang.domain.user.UserRepository;
import com.khpl.uzikbbang.domain.user.UzikUser;
import com.khpl.uzikbbang.exception.InvalidSignInException;

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

        assertEquals("황인태", user.getName());
        assertEquals("hwang@hwang.com", user.getEmail());
        assertTrue(passwordEncoder.matches("1234", user.getPassword()));
    }

    @Test
    @DisplayName("처음 가입한 사람의 level은 BEGINNER 이다.")
    void testBeginner() {
        SignUp signUp = SignUp.builder()
                .name("황인태")
                .email("hwang@hwang.com")
                .password("1234")
                .build();

        UzikUser user = authService.signUp(signUp);

        assertThat(user.getLevel()).isEqualTo(Level.BEGINNER);
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

        authService.signUp(signUp);

        SignIn signIn = SignIn.builder()
                .email("hwang@hwang.com")
                .password("1234")
                .build();

        UzikUser resultUser = authService.signIn(signIn);
        Session session = authService.getSession(resultUser.getId());

        assertEquals(resultUser.getId(), session.getUser().getId());
    }

    @Test
    @DisplayName("회원 email이 틀릴 경우 로그인 실패 테스트")
    void testNotSignInEmail() {
        SignUp signUp = SignUp.builder()
                .name("황인태")
                .email("hwang@hwang.com")
                .password("1234")
                .build();

        authService.signUp(signUp);

        SignIn signIn = SignIn.builder()
                .email("hwang@intae.com")
                .password("1234")
                .build();

        assertThrows(InvalidSignInException.class, () -> authService.signIn(signIn));
    }

    @Test
    @DisplayName("회원 비밀번호가 틀릴 경우 로그인 실패 테스트")
    void testWrongPassword() {
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

        UzikUser resultUser = userService.updateUseAt(user.getId(), false);

        assertTrue(resultUser.isUseAt() == false);
    }

    @Test
    @DisplayName("사용자가 로그아웃을 하면 refreshToken을 지운다.")
    void testSignOut() {
        SignUp signUp = SignUp.builder()
                .name("황인태")
                .email("hwang@hwang.com")
                .password("1234")
                .build();

        authService.signUp(signUp);

        SignIn signIn = SignIn.builder()
                .email("hwang@hwang.com")
                .password("1234")
                .build();

        UzikUser signInUser = authService.signIn(signIn);
        Long signInUserId = signInUser.getId();

        String refreshToken = authService.createRefreshToken(signInUserId);
        UzikUser findSignInUser = userService.findById(signInUserId).get();

        SignOut signOut = SignOut.builder()
                .email("hwang@hwang.com")
                .password("1234")
                .build();

        UzikUser signOutUser = authService.signOut(signOut);
        UzikUser findSignOutUser = userService.findById(signOutUser.getId()).get();

        assertEquals(refreshToken, findSignInUser.getRefreshToken());
        assertTrue(findSignOutUser.getRefreshToken().isBlank());
    }

    
}
