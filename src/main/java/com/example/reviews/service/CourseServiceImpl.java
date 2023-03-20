package com.example.reviews.service;

import com.example.reviews.exceptions.ReviewsBadRequestException;
import com.example.reviews.exceptions.ReviewsResourceNotFoundException;
import com.example.reviews.model.Course;
import com.example.reviews.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course getCourseById(Integer courseId) throws ReviewsResourceNotFoundException {
        return courseRepository.findById(courseId);
    }

    @Override
    public Course addCourse(String title, String url, String provider, String subject) throws ReviewsBadRequestException {
        int courseId = courseRepository.create(title, url, provider, subject);
        return courseRepository.findById(courseId);
    }

    @Override
    public void updateCourse(Integer courseId, Course course) throws ReviewsBadRequestException {
        courseRepository.update(courseId, course);
    }

    @Override
    public void removeCourse(Integer courseId) throws ReviewsResourceNotFoundException {
        this.getCourseById(courseId);
        courseRepository.removeById(courseId);
    }


}
