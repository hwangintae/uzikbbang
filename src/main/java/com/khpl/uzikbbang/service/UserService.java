package com.khpl.uzikbbang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.Page;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UzikUser> getList(Page page) {
        return userRepository.getList(page);
    }

    public void save() {
        
    }
}
