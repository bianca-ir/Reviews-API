package com.example.reviews.service;

import com.example.reviews.exceptions.ReviewsAuthException;
import com.example.reviews.model.User;

public interface UserService {
    User validateUser(String email, String password) throws ReviewsAuthException;

    User registerUser(String firstName, String email, String password) throws ReviewsAuthException;

}
