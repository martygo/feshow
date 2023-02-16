package com.martygo.feshow.config;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorCustom {
    private HttpStatus status;
    private String message;
    private Object errors;

    public ErrorCustom(HttpStatus status, String message, Object errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
