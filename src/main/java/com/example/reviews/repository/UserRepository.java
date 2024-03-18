package com.example.reviews.repository;

import com.example.reviews.exceptions.ReviewsAuthException;
import com.example.reviews.model.User;

public interface UserRepository {

    Integer create(String firstName, String email, String password);

    User findByEmailAndPassword(String email, String password) throws ReviewsAuthException;

    User findById(Integer userId);


}
