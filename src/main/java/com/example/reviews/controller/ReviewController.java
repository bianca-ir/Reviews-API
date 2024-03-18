package com.example.reviews.controller;

import com.example.reviews.exceptions.ReviewsResourceNotFoundException;
import com.example.reviews.model.Course;
import com.example.reviews.model.Review;
import com.example.reviews.service.CourseService;
import com.example.reviews.service.ReviewService;
import com.example.reviews.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/reviews")
public class ReviewController {


        @Autowired
        ReviewService reviewService;
        @Autowired
        UserService userService;
        @Autowired
        CourseService courseService;


        @GetMapping("/{courseId}")
        public ResponseEntity<Review> getReviewsByCourse(@PathVariable("courseId") Integer courseId) {
            Review review = reviewService.getReviewByCourseId(courseId);
            return new ResponseEntity<>(review, HttpStatus.OK);
        }


        @PostMapping("")
        public ResponseEntity<Review> addReview (@RequestBody Map<String, Object> reviewMap){


            String firstName = (String) reviewMap.get("firstName");
            String courseName = (String) reviewMap.get("courseName");
            String description = (String) reviewMap.get("description");


            Integer userId = userService.getIdByName(firstName);
            Integer courseId = courseService.getIdByName(courseName);

            Review review = reviewService.addReview(userId, courseId, description);
            return new ResponseEntity<>(review, HttpStatus.CREATED);
        }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable("reviewId") Integer reviewId,
                                               @RequestBody Map<String, Object> reviewMap) {

        String firstName = (String) reviewMap.get("firstName");
        String courseName = (String) reviewMap.get("courseName");
        String description = (String) reviewMap.get("description");


        Integer userId = userService.getIdByName(firstName);
        Integer courseId = courseService.getIdByName(courseName);


        if (userId == null || courseId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Update the review
        try {
            Review updatedReview = reviewService.updateReview(reviewId, userId, courseId, description);
            return ResponseEntity.ok(updatedReview);
        } catch (ReviewsResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



}


