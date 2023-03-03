package com.martygo.feshow.dtos;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

@Data
public class CategoryDTO {

    @NotEmpty(message = "Please provide a name")
    private String name;
}
