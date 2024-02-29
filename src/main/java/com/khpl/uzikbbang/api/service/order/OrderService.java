package com.khpl.uzikbbang.api.service.order;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;

}
