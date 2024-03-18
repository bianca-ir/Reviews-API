package com.example.reviews.repository;

import com.example.reviews.exceptions.ReviewsBadRequestException;
import com.example.reviews.exceptions.ReviewsResourceNotFoundException;
import com.example.reviews.model.Course;

public interface CourseRepository {

    Course findById(Integer courseId) throws ReviewsResourceNotFoundException;

    Integer create(String title, String url, String provider, String subject) throws ReviewsBadRequestException;

    void update(Integer courseId, Course course) throws ReviewsBadRequestException;

    void removeById(Integer courseId);

    Integer getIdByName(String courseName);
}
