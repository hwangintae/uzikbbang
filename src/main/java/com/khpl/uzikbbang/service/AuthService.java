package com.khpl.uzikbbang.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.crypto.PasswordEncoder;
import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.exception.AlreadySignUpEmailException;
import com.khpl.uzikbbang.exception.BadCredentialsException;
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
    private final PasswordEncoder passwordEncoder;

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

        user.addSession();

        return user;
    }

    public UzikUser findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new BadCredentialsException());
    }

    public UzikUser findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new BadCredentialsException());
    }

    public void save(UzikUser user) {
        userRepository.save(user);
    }
}
