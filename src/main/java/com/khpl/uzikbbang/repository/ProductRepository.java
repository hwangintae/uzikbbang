package com.khpl.uzikbbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryDsl{
    
}
