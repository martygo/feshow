package com.martygo.feshow.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;

    @NotEmpty(message = "Please provide a name")
    private String name;

    private String slug;
}
