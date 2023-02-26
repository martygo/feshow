package com.martygo.feshow.services;

import java.util.Optional;

import com.martygo.feshow.domain.Movie;
import com.martygo.feshow.repo.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    public Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

    public boolean existsByTitle(String title) {
        return movieRepository.existsByTitle(title);
    }
}
