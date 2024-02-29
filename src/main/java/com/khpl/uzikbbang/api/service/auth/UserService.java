package com.khpl.uzikbbang.api.service.auth;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.api.controller.Page;
import com.khpl.uzikbbang.domain.user.UserRepository;
import com.khpl.uzikbbang.domain.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> getList(Page page) {
        return userRepository.getList(page);
    }

    @Transactional
    public User updateUseAt(Long id, boolean bool) {
        User user = userRepository.findById(id).get();
        user.updateUseAt(bool);

        return user;
    }
    
}
