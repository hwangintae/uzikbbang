package com.khpl.uzikbbang.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.api.controller.auth.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
    
}
