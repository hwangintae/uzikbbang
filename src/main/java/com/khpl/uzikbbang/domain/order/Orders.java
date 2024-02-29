package com.khpl.uzikbbang.domain.order;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long userId;
    private Long productId;

    private LocalDateTime createDt;

    @Builder
    public Orders(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
        this.createDt = LocalDateTime.now();
    }
}
