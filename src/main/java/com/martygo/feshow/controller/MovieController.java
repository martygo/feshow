package com.martygo.feshow.controller;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import com.martygo.feshow.domain.Movie;
import com.martygo.feshow.services.MovieService;
import com.martygo.feshow.dtos.MovieDTO;
import com.martygo.feshow.handleError.MovieError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.BeanUtils;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody MovieDTO movieDTO) {
        String errorMessage = String.format("Movie with title %s already exists", movieDTO.getTitle());

        Movie movie = new Movie();

        BeanUtils.copyProperties(movieDTO, movie);

        if(movieService.existsByTitle(movieDTO.getTitle())) {
            log.error(errorMessage);

            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MovieError(errorMessage));
        }

        movie.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        movie.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        log.info("Saving movie: {}", movie);
        return new ResponseEntity<Movie>(movieService.create(movie), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Movie>> get() {
        log.info("Getting all movies");

        return new ResponseEntity<Iterable<Movie>>(movieService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        log.info("Getting movie by id: {}", id);

        Optional<Movie> movie = movieService.findById(id);

        if (!movie.isPresent()) {
            return new ResponseEntity<Object>("Movie not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Object>(movie.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("Deleting movie by id: {}", id);

        Optional<Movie> movieOptional = movieService.findById(id);

        if (!movieOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MovieError("Movie not found"));
        }

        if (movieOptional.get().getId() != id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MovieError("Movie not found"));
        }

        movieService.delete(movieOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody @Valid MovieDTO movieDTO) {
        log.info("Updating movie by id: {}", id);

        String errorMessage = String.format("Movie not found with id %s", id.toString());

        Optional<Movie> movieOptional = movieService.findById(id);

        Movie movie = new Movie();

        BeanUtils.copyProperties(movieDTO, movie);

        if (!movieOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MovieError(errorMessage));
        }

        if (movieOptional.get().getId() != id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MovieError(errorMessage));
        }

        movie.setId(movieOptional.get().getId());
        movie.setCreatedAt(movieOptional.get().getCreatedAt());
        movie.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        return new ResponseEntity<Movie>(movieService.create(movie), HttpStatus.OK);
    }
}
