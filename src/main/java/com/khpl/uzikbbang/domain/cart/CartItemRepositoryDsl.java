package com.khpl.uzikbbang.domain.cart;

import java.util.Optional;

public interface CartItemRepositoryDsl {
    Optional<CartItem> getCartItem(Long userId, Long productId);
}
