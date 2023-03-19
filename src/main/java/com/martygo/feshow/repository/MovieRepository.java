package com.martygo.feshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.martygo.feshow.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitle(String title);
}
