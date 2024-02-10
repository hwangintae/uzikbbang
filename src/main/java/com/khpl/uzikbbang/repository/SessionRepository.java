package com.khpl.uzikbbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.dto.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
    
}
