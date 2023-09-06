package com.khpl.uzikbbang.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.Page;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UzikUser> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UzikUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(UzikUser user) {
        userRepository.save(user);
    }

    public List<UzikUser> getList(Page page) {
        return userRepository.getList(page);
    }

    @Transactional
    public UzikUser updateUseAt(Long id, boolean bool) {
        UzikUser user = userRepository.findById(id).get();
        user.updateUseAt(bool);

        return user;
    }
    
}
