package com.khpl.uzikbbang.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.CartItem;
import com.khpl.uzikbbang.domain.UzikOrderProduct;
import com.khpl.uzikbbang.domain.Product;
import com.khpl.uzikbbang.domain.UzikUser;

@Service
public class OrderService {

    @Transactional
    public List<UzikOrderProduct> orderByProduct(UzikUser user, Product product, int cnt) {
        return user.addOrder().orderByProduct(product, cnt);
    }

    @Transactional
    public List<UzikOrderProduct> orderByCartItem(UzikUser user, List<CartItem> cartItems) {
        return user.addOrder().orderByCartItem(cartItems);
    }
}
