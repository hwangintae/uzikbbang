package com.khpl.uzikbbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.domain.UzikUser;

public interface UserRepository extends JpaRepository<UzikUser, Long>, UserRepositoryDsl{
    
}
