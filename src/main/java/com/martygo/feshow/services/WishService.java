package com.martygo.feshow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martygo.feshow.domain.Wish;
import com.martygo.feshow.repository.WishRepository;

@Service
public class WishService {
    @Autowired
    private WishRepository wishRepository;

    public Iterable<Wish> findAll() {
        return wishRepository.findAll();
    }
}
