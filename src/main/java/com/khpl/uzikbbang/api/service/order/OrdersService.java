package com.khpl.uzikbbang.api.service.order;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.order.Orders;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersService {
    
    private final OrdersRepository orderRepository;

    public Orders productOrder(Orders order) {
        return orderRepository.save(order);
    }

}
