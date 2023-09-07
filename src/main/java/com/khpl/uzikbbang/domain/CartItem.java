package com.khpl.uzikbbang.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private UzikUser user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int cnt;

    private Boolean useAt;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate registDt;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate updtDt;

    @Builder
    public CartItem(UzikUser user, Product product) {
        this.user = user;
        this.product = product;
        this.cnt = 1;
        this.useAt = true;
        this.registDt = LocalDate.now();
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
        this.updtDt = LocalDate.now();
    }

    public void updateUseAt(boolean bool) {
        this.useAt = bool;
    }
}
