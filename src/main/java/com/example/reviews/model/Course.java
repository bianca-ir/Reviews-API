package com.example.reviews.model;

public class Course {
    private Integer courseId;
    private String title;
    private String url;
    private String provider;
    private String subject;

    public Course(Integer courseId, String title, String url, String provider, String subject) {
        this.courseId = courseId;
        this.title = title;
        this.url = url;
        this.provider = provider;
        this.subject = subject;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
