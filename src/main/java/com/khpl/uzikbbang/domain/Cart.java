package com.khpl.uzikbbang.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    public CartItem addCartItem(Product product) {
        CartItem cartItem = CartItem.builder()
            .product(product)
            .cart(this)
        .build();

        cartItems.add(cartItem);

        return cartItem;
    }
}
