package com.khpl.uzikbbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.dto.UzikOrderProduct;

public interface OrderProductRepository extends JpaRepository<UzikOrderProduct, Long>, OrderProductRepositoryDsl {
    
}
