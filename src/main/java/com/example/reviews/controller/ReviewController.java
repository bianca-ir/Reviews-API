package com.example.reviews.controller;

import com.example.reviews.model.Course;
import com.example.reviews.model.Review;
import com.example.reviews.service.CourseService;
import com.example.reviews.service.ReviewService;
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
//
//        @GetMapping("/{userId}")
//        public ResponseEntity<Review> getReviewsByUser(@PathVariable("userId") Integer userId) {
//            Review review = reviewService.getReviewById(userId);
//            return new ResponseEntity<>(review, HttpStatus.OK);
//
//
//        }
//
//        @GetMapping("/{courseId}")
//        public ResponseEntity<Course> getReviewsByCourse(@PathVariable("courseId") Integer courseId) {
//            Course course = courseService.getCourseById(courseId);
//            return new ResponseEntity<>(course, HttpStatus.OK);
//
//
//    }

        @PostMapping("")
        public ResponseEntity<Review> addReview (@RequestBody Map<String, Object> reviewMap){
            Integer userId = (Integer) reviewMap.get("userId");
            Integer courseId = (Integer) reviewMap.get("courseId");
            String description = (String) reviewMap.get("description");
            Review review = reviewService.addReview(userId, courseId, description);
            return new ResponseEntity<>(review, HttpStatus.CREATED);
        }


    }


