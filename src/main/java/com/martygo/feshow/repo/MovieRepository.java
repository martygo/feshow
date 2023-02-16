package com.martygo.feshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.martygo.feshow.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
}
