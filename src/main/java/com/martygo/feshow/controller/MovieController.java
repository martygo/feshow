package com.martygo.feshow.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.martygo.feshow.domain.Movie;
import com.martygo.feshow.services.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> save(@Valid @RequestBody Movie movie) {
        Movie savedMovie = movieService.create(movie);

        return new ResponseEntity<Movie>(savedMovie, HttpStatus.CREATED);
    }
}
