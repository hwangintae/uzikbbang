package com.khpl.uzikbbang.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.crypto.PasswordEncoder;
import com.khpl.uzikbbang.domain.Session;
import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.exception.AlreadySignUpEmailException;
import com.khpl.uzikbbang.exception.InvalidSignInException;
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.SignIn;
import com.khpl.uzikbbang.request.SignUp;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UzikUser signUp(SignUp signUp) {
        String email = signUp.getEmail();

        // 이메일 중복 체크
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new AlreadySignUpEmailException();
        });

        String password = passwordEncoder.encrypt(signUp.getPassword());

        UzikUser user = UzikUser.builder()
            .name(signUp.getName())
            .email(email)
            .password(password)
        .build();
         
        userRepository.save(user);

        return user;
    }

    @Transactional
    public UzikUser signIn(SignIn signIn) {
        UzikUser user = userRepository.findByEmail(signIn.getEmail())
            .orElseThrow(InvalidSignInException::new);

        boolean isMatches = passwordEncoder.matches(signIn.getPassword(), user.getPassword());

        if (isMatches == false) {
            throw new InvalidSignInException();
        }        

        user.addSession();

        return user;
    }

    @Transactional
    public Session getSession(Long userId) {
        UzikUser user = userRepository.findById(userId)
            .orElseThrow(InvalidSignInException::new);

        return user.addSession();
    }
}
