package com.khpl.uzikbbang.api.controller.order;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.khpl.uzikbbang.domain.product.Product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UzikOrderProduct {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UzikOrder order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private int orderPrice;
    private int cnt;

    @Builder
    public UzikOrderProduct(UzikOrder order, Product product, int orderPrice, int cnt) {
        this.order = order;
        this.product = product;
        this.orderPrice = orderPrice;
        this.cnt  = cnt;
    }
}
