package com.khpl.uzikbbang.api.controller.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.khpl.uzikbbang.domain.cart.CartItem;
import com.khpl.uzikbbang.domain.product.Product;
import com.khpl.uzikbbang.domain.user.UzikUser;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UzikOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UzikUser user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<UzikOrderProduct> orderProducts = new ArrayList<>();

    private LocalDateTime createDt;

    public UzikOrder(UzikUser user) {
        this.user = user;
        this.createDt = LocalDateTime.now();
    }

    public List<UzikOrderProduct> addOrderProducts(List<CartItem> cartItems) {
        List<UzikOrderProduct> list = cartItems.stream()
            .map(cartItem -> {
                Product product = cartItem.getProduct();

                return UzikOrderProduct.builder()
                    .order(this)
                    .product(product)
                    .orderPrice(product.getPrice())
                    .cnt(cartItem.getCnt())
                .build();
            })
        .collect(Collectors.toList());
        
        this.orderProducts.addAll(list);

        return this.orderProducts;
    }

    public List<UzikOrderProduct> addOrderProducts(Product product, int cnt) {
        UzikOrderProduct orderProduct = UzikOrderProduct.builder()
            .order(this)
            .product(product)
            .orderPrice(product.getPrice())
            .cnt(cnt)
        .build();

        this.orderProducts.add(orderProduct);

        return this.orderProducts;
    }
}
