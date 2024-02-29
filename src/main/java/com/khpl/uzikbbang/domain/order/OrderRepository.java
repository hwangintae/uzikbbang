package com.khpl.uzikbbang.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.api.controller.order.UzikOrder;

public interface OrderRepository extends JpaRepository<UzikOrder, Long> {
    
}
