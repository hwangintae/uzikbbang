package com.khpl.uzikbbang.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;

@Entity
@Getter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id")
    private Cart cart;

    private int cnt;

    private Boolean useAt;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate registDt;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate updtDt;

    @PrePersist
    public void initEntity() {
        this.registDt = LocalDate.now();
        this.useAt = true;
    }
}
