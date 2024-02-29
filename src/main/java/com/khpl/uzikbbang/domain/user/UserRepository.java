package com.khpl.uzikbbang.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UzikUser, Long>, UserRepositoryDsl{
    Optional<UzikUser> findByEmail(String email);
}
