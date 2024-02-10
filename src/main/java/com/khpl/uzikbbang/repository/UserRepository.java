package com.khpl.uzikbbang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.entity.UzikUser;


public interface UserRepository extends JpaRepository<UzikUser, Long>, UserRepositoryDsl{
    Optional<UzikUser> findByEmail(String email);
}
