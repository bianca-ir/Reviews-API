package com.example.reviews.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewsResourceNotFoundException extends RuntimeException {
    public ReviewsResourceNotFoundException(String message) {
        super(message);
    }
}
