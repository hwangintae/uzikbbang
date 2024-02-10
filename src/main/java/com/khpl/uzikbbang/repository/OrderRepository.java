package com.khpl.uzikbbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.dto.UzikOrder;

public interface OrderRepository extends JpaRepository<UzikOrder, Long> {
    
}
