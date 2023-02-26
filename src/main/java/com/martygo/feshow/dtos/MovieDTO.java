package com.martygo.feshow.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class MovieDTO {
    @NotEmpty(message = "Please provide a title")
    @Size(min = 3, message = "Title must be at least 3 characters long")
    private String title;

    @NotEmpty(message = "Please provide a link to the poster")
    private String poster;

    private String description;
    private String trailer;
    private String genre;
    private Boolean isRelease;
    private int year;
}
