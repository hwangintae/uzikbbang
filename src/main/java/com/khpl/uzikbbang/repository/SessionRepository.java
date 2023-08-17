package com.khpl.uzikbbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.domain.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
    
}
