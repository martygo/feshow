package com.martygo.feshow.controller;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping
    public ResponseEntity<String> get() {
        return new ResponseEntity<String>("Get all...", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById() {
        return new ResponseEntity<String>("Getting one....", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> update() {
        return new ResponseEntity<String>("Update...", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete() {
        return new ResponseEntity<String>("Delete...", HttpStatus.OK);
    }
}
