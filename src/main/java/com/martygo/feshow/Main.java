package com.martygo.feshow;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martygo.feshow.domain.Movie;
import com.martygo.feshow.repository.MovieRepository;

@SpringBootApplication
@RestController
public class Main {

    @Value("${application.name}")
    private String appName;

    @Bean
    @Profile("dev")
    CommandLineRunner commandLineRunner(@Autowired MovieRepository movieRepository) {
        return args -> {
            System.out.println("Desenvolvimento!");

            Movie movie = Movie.builder()
                    .title("The Matrix")
                    .description("movie")
                    .poster("poster")
                    .trailer("poster")
                    .genre("action")
                    .isRelease(true)
                    .year(1999)
                    .createdAt(LocalDateTime.now(ZoneId.of("UTC")))
                    .updatedAt(LocalDateTime.now(ZoneId.of("UTC")))
                    .build();

            movieRepository.save(movie);
        };
    }

    @GetMapping("/")
    public String init() {
        return appName;
    }

    public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
