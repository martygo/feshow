package com.martygo.feshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martygo.feshow.domain.Wish;
import com.martygo.feshow.services.WishService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/wish")
public class WishController {
    @Autowired
    private WishService wishService;

    @GetMapping
    public ResponseEntity<Iterable<Wish>> get() {
        log.info("Getting all wishes");

        return new ResponseEntity<Iterable<Wish>>(wishService.findAll(), HttpStatus.OK);
    }
}
