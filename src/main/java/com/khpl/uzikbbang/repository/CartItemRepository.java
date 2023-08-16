package com.khpl.uzikbbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.domain.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
}
