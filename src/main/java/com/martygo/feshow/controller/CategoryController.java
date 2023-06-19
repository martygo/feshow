package com.martygo.feshow.controller;

import com.martygo.feshow.services.CategoryService;
import com.martygo.feshow.domain.Category;
import com.martygo.feshow.dtos.CategoryDTO;
import com.martygo.feshow.handleError.HandleError;

import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody CategoryDTO categoryDTO) {
        String errorMessage = String.format("Category with name %s already exists", categoryDTO.getName());

        Category category = new Category();

        BeanUtils.copyProperties(categoryDTO, category);

        if(categoryService.existsByName(categoryDTO.getName())) {
            log.error(errorMessage);

            return ResponseEntity.status(HttpStatus.CONFLICT).body(new HandleError(errorMessage));
        }

        String slug = category.getName().toLowerCase().replace(" ", "-");

        category.setSlug(slug);

        log.info("Saving category: {}", category);
        return new ResponseEntity<Category>(categoryService.create(category), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> get() {
        return new ResponseEntity<Iterable<Category>>(categoryService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("Deleting movie by id: {}", id);

        Optional<Category> categoryOptional = categoryService.findById(id);

        if(categoryOptional.isEmpty()) {
            log.error("Category with id {} not found", id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HandleError("Category not found"));
        }

        if (categoryOptional.get().getId() != id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HandleError("Movie not found"));
        }

        categoryService.delete(categoryOptional.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
