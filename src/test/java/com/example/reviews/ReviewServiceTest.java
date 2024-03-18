package com.example.reviews;

import com.example.reviews.model.Review;
import com.example.reviews.model.User;
import com.example.reviews.repository.ReviewRepository;
import com.example.reviews.repository.ReviewRepositoryImpl;
import com.example.reviews.repository.UserRepository;
import com.example.reviews.service.ReviewService;
import com.example.reviews.service.ReviewServiceImpl;
import com.example.reviews.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.List;



    public class ReviewServiceTest {

        @Mock
        private ReviewRepositoryImpl reviewRepository;

        @InjectMocks
        private ReviewServiceImpl reviewService;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }


        @Test
        public void testGetAllReviews() {
            // Arrange
            List<Review> reviewList = new ArrayList<>();
            reviewList.add(new Review(1, 1,  2, "good"));

            when(reviewRepository.findAll(1,1)).thenReturn(reviewList);

            // Act
            List<Review> result = reviewService.getAllReviews(1,1);

            // Assert
            assertEquals(reviewList, result);
        }



    }



