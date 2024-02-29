package com.khpl.uzikbbang.api.service.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.domain.order.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    
}
