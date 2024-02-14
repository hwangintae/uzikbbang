package com.khpl.uzikbbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    
}
