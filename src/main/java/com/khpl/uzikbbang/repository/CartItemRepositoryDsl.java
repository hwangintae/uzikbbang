package com.khpl.uzikbbang.repository;

import java.util.Optional;

import com.khpl.uzikbbang.entity.CartItem;

public interface CartItemRepositoryDsl {
    Optional<CartItem> getCartItem(Long userId, Long productId);
}
