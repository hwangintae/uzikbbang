package com.khpl.uzikbbang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>, CartItemRepositoryDsl {
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
    List<CartItem> findAllByUserId(Long userId);
}
