package com.martygo.feshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martygo.feshow.domain.Wish;

public interface WishRepository extends JpaRepository<Wish, Long> {
}
