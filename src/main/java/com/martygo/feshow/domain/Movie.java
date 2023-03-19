package com.martygo.feshow.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "movies")
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private String poster;
    private String trailer;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    private Boolean isRelease;
    private int year;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
