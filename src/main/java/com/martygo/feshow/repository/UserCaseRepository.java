package com.martygo.feshow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martygo.feshow.domain.UserCase;

public interface UserCaseRepository extends JpaRepository<UserCase, Long> {
    Optional<UserCase> findByEmail(String email);
    boolean existsByEmail(String email);
}
