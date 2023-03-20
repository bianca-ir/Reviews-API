package com.example.reviews.service;

import com.example.reviews.exceptions.ReviewsBadRequestException;
import com.example.reviews.exceptions.ReviewsResourceNotFoundException;
import com.example.reviews.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Integer userId, Integer courseId);

    Review getReviewById(Integer userId, Integer courseId, Integer reviewId) throws ReviewsResourceNotFoundException;

    Review addReview(Integer userId, Integer courseId, String description) throws ReviewsResourceNotFoundException;

    void updateReview(Integer userId, Integer courseId, Integer reviewId, Review review) throws ReviewsBadRequestException;

    void removeReview(Integer userId, Integer courseId, Integer reviewId) throws ReviewsResourceNotFoundException;


}
