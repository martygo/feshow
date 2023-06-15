package com.martygo.feshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martygo.feshow.domain.UserCase;
import com.martygo.feshow.handleError.HandleError;
import com.martygo.feshow.services.UserCaseService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserCaseController {
    @Autowired
    private UserCaseService userCaseService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UserCase user) {
        String errorMessage = String.format("User with email %s already exists", user.getEmail());

        if(userCaseService.existsByEmail(user.getEmail())) {
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new HandleError(errorMessage));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        log.info("Saving user: {}", user);
        return new ResponseEntity<UserCase>(userCaseService.create(user), HttpStatus.CREATED);
    }
}
