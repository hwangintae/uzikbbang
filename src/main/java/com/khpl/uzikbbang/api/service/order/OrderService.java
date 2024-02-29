package com.khpl.uzikbbang.api.service.order;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.order.Orders;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;

    public Orders productOrder(Orders order) {
        return orderRepository.save(order);
    }

}
