package com.khpl.uzikbbang.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.Session;
import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.exception.AlreadySignUpEmailException;
import com.khpl.uzikbbang.exception.InvalidSignInException;
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.Page;
import com.khpl.uzikbbang.request.SignIn;
import com.khpl.uzikbbang.request.SignUp;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public List<UzikUser> getList(Page page) {
        return userRepository.getList(page);
    }

    public UzikUser signUp(SignUp signUp) {
        String email = signUp.getEmail();

        // 이메일 중복 체크
        boolean isPresent = userRepository.findByEmail(email).isPresent();

        if (isPresent) {
            throw new AlreadySignUpEmailException();
        }

        // 암호화 구현 해야함
        String passWord = signUp.getPassWord();

        UzikUser user = UzikUser.builder()
            .name(signUp.getName())
            .email(email)
            .passWord(passWord)
        .build();
        
        userRepository.save(user);

        return user;
    }

    @Transactional
    public Session signIn(SignIn signIn) {
        UzikUser user = userRepository.findByEmailAndPassWord(signIn.getEmail(), signIn.getPassWord())
            .orElseThrow(InvalidSignInException::new);

        return user.addSession();
    }
}
