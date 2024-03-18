package com.example.reviews.service;

import com.example.reviews.exceptions.ReviewsBadRequestException;
import com.example.reviews.exceptions.ReviewsResourceNotFoundException;
import com.example.reviews.model.Course;

import java.util.List;

public interface CourseService {

    Course getCourseById(Integer courseId) throws ReviewsResourceNotFoundException;

    Course addCourse(String title, String url, String provider, String subject) throws ReviewsBadRequestException;

    void updateCourse(Integer courseId, Course course) throws ReviewsBadRequestException;

    void removeCourse(Integer courseId) throws ReviewsResourceNotFoundException;


    Integer getIdByName(String courseName);
}
