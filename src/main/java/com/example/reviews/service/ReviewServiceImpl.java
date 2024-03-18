package com.example.reviews.service;

import com.example.reviews.exceptions.ReviewsBadRequestException;
import com.example.reviews.exceptions.ReviewsResourceNotFoundException;
import com.example.reviews.model.Review;
import com.example.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements  ReviewService{
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews(Integer userId, Integer courseId) {
        return reviewRepository.findAll(userId, courseId);
    }

    @Override
    public Review getReviewById(Integer userId, Integer courseId, Integer reviewId) throws ReviewsResourceNotFoundException {
        return reviewRepository.findById(userId, courseId, reviewId);
    }

    @Override
    public Review getReviewByCourseId(Integer courseId) throws ReviewsResourceNotFoundException {
        return reviewRepository.findByCourseId(courseId);
    }



    @Override
    public Review addReview(Integer userId, Integer courseId, String description) throws ReviewsResourceNotFoundException {
        int reviewId = reviewRepository.create(userId, courseId, description);
        return reviewRepository.findById(userId, courseId, reviewId);
    }

    @Override
    public Review updateReview(Integer userId, Integer courseId, Integer reviewId, String description) throws ReviewsBadRequestException {
        reviewRepository.update(userId, reviewId, courseId, description);
        return reviewRepository.findById(userId, courseId, reviewId);
    }

    @Override
    public void removeReview(Integer userId, Integer courseId, Integer reviewId) throws ReviewsResourceNotFoundException {
        reviewRepository.removeById(userId, reviewId, courseId);
    }

}
