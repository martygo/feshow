package com.martygo.feshow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martygo.feshow.domain.Movie;
import com.martygo.feshow.repo.MovieRepository;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }
}
