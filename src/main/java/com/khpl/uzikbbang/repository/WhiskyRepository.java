package com.khpl.uzikbbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.entity.Whisky;
import java.util.List;


public interface WhiskyRepository extends JpaRepository<Whisky, Long> {
    List<Whisky> findByMenuId(Long menuId);
}
