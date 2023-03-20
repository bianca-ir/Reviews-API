package com.example.reviews.model;

public class Review {
    private Integer reviewId;
    private Integer userId;
    private Integer courseId;
    private String description;

    public Review(Integer reviewId, Integer userId, Integer courseId, String description) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.courseId = courseId;
        this.description = description;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
