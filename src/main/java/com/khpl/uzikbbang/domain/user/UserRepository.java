package com.khpl.uzikbbang.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryDsl{
    Optional<User> findByEmail(String email);
}
