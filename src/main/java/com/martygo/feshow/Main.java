package com.martygo.feshow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.martygo.feshow.domain.Movie;
import com.martygo.feshow.repo.MovieRepository;

@SpringBootApplication
public class Main {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired MovieRepository movieRepository) {
		return args -> {
			Movie movie = Movie.builder()
			.title("The Matrix")
			.description("movie")
			.poster("poster")
			.trailer("poster")
			.genre("action")
			.isRelease(true)
			.year(1999)
			.build();

			movieRepository.save(movie);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
