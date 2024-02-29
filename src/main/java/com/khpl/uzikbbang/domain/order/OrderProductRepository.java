package com.khpl.uzikbbang.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.api.controller.order.UzikOrderProduct;

public interface OrderProductRepository extends JpaRepository<UzikOrderProduct, Long>, OrderProductRepositoryDsl {
    
}
