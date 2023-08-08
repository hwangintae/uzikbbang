package com.khpl.uzikbbang.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    @ManyToOne
    private Cart cart;

    private int cnt;

    private Boolean useAt;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate registDt;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate updtDt;

    @Builder
    public CartItem(Product product, Cart cart) {
        this.product = product;
        this.cart = cart;
        this.cnt = 1;
        this.useAt = true;
        this.registDt = LocalDate.now();
    }
}
