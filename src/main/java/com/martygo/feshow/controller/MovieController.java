package com.martygo.feshow.controller;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.martygo.feshow.domain.Movie;
import com.martygo.feshow.services.MovieService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> save(@Valid @RequestBody Movie movie) {
        movie.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        movie.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        log.info("Saving movie: {}", movie);

        return new ResponseEntity<Movie>(movieService.create(movie), HttpStatus.CREATED);
    }
}
