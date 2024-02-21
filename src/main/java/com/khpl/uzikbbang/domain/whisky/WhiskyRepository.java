package com.khpl.uzikbbang.domain.whisky;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WhiskyRepository extends JpaRepository<Whisky, Long> {
    List<Whisky> findByMenuId(Long menuId);
}
