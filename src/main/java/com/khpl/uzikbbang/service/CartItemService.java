package com.khpl.uzikbbang.service;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.CartItem;
import com.khpl.uzikbbang.domain.Product;
import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.repository.CartItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItem addItem(UzikUser user, Product product) {
        CartItem cartItem = CartItem.builder()
            .user(user)
            .product(product)
        .build();

        cartItemRepository.save(cartItem);
        
        return cartItem;
    }
}
