package com.khpl.uzikbbang.domain;

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

import lombok.Getter;

@Getter
@Entity
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

    public List<UzikOrderProduct> orderByCartItem(List<CartItem> cartItems) {
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

    public List<UzikOrderProduct> orderByProduct(Product product, int cnt) {
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
