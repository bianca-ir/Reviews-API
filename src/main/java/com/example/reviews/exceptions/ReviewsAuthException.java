package com.example.reviews.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ReviewsAuthException extends RuntimeException {

    public ReviewsAuthException(String message) {
        super(message);
    }
}
