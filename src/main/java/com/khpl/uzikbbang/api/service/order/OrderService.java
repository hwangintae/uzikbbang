package com.khpl.uzikbbang.api.service.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.api.controller.Page;
import com.khpl.uzikbbang.domain.order.OrderProductRepository;
import com.khpl.uzikbbang.domain.order.UzikOrderProduct;
import com.khpl.uzikbbang.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final OrderProductRepository orderProductRepository;

    public List<UzikOrderProduct> getOrderProducts(Page page, Long userId) {
        return orderProductRepository.getList(page, userId);
    }
}
