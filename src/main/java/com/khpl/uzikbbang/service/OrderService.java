package com.khpl.uzikbbang.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.CartItem;
import com.khpl.uzikbbang.domain.UzikOrderProduct;
import com.khpl.uzikbbang.domain.Product;
import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.repository.OrderProductRepository;
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.Page;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final OrderProductRepository orderProductRepository;

    @Transactional
    public List<UzikOrderProduct> addOrderProducts(UzikUser user, Product product, int cnt) {
        UzikUser findUser = userRepository.findById(user.getId()).get();
        return findUser.addOrder().addOrderProducts(product, cnt);
    }

    @Transactional
    public List<UzikOrderProduct> addOrderProducts(UzikUser user, List<CartItem> cartItems) {
        return user.addOrder().addOrderProducts(cartItems);
    }

    public List<UzikOrderProduct> getOrderProducts(Page page, Long userId) {
        return orderProductRepository.getList(page, userId);
    }
}
