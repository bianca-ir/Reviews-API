package com.example.reviews.controller;

import com.example.reviews.model.Course;
import com.example.reviews.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable("courseId") Integer courseId) {
        Course course = courseService.getCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<Course> addCourse(@RequestBody Map<String, Object> courseMap){
        String title = (String) courseMap.get("title");
        String url = (String) courseMap.get("url");
        String provider = (String) courseMap.get("provider");
        String subject = (String) courseMap.get("subject");
        Course course = courseService.addCourse(title, url, provider, subject);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }


}
