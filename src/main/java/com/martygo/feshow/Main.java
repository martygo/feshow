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

import com.martygo.feshow.domain.Category;
import com.martygo.feshow.domain.Movie;
import com.martygo.feshow.domain.Wish;
import com.martygo.feshow.repository.MovieRepository;
import com.martygo.feshow.repository.WishRepository;

@SpringBootApplication
@RestController
public class Main {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    @Profile("dev")
    CommandLineRunner commandLineRunner(@Autowired MovieRepository movieRepository, @Autowired WishRepository wishRepository) {
        return args -> {

            Movie movie = Movie.builder()
                    .title("The Matrix")
                    .description("movie")
                    .poster("poster")
                    .trailer("poster")
                    .isRelease(true)
                    .year(1999)
                    .createdAt(LocalDateTime.now(ZoneId.of("UTC")))
                    .updatedAt(LocalDateTime.now(ZoneId.of("UTC")))
                    .build();

            Category category = Category.builder()
                    .name("Action")
                    .slug("action")
                    .movie(movie)
                    .build();

            Wish wish = Wish.builder()
                    .username("martygo")
                    .movie("The Matrix")
                    .createdAt(LocalDateTime.now(ZoneId.of("UTC")))
                    .updatedAt(LocalDateTime.now(ZoneId.of("UTC")))
                    .build();

            movie.getCategories().add(category);
            
            movieRepository.save(movie);
            wishRepository.save(wish);
        };
    }

    @GetMapping("/welcome")
    public String init() {
        return appName;
    }

    public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
