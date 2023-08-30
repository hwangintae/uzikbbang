package com.khpl.uzikbbang.service;

import java.util.Optional;

import javax.transaction.Transactional;

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

    // [v] 장바구니에 상품 추가 시, 이미 장바구니에 있는경우  cnt 만 올리기
    @Transactional
    public CartItem addItems(UzikUser user, Product product) {
        Optional<CartItem> findCartItem = cartItemRepository.getCartItem(user.getId(), product.getId());

        if (findCartItem.isPresent()) {
            findCartItem.get().addCnt();
            
            return findCartItem.get();
        } else {
            CartItem cartItem = CartItem.builder()
                .user(user)
                .product(product)
            .build();

            return cartItemRepository.save(cartItem);
        }
    }
}
