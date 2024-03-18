package com.example.reviews.repository;

import com.example.reviews.exceptions.ReviewsBadRequestException;
import com.example.reviews.exceptions.ReviewsResourceNotFoundException;
import com.example.reviews.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String SQL_CREATE = "INSERT INTO COURSES(COURSE_ID, TITLE, URL, PROVIDER, SUBJECT) VALUES(DEFAULT, ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID = "SELECT COURSE_ID, TITLE, URL, PROVIDER, SUBJECT " +
            "FROM COURSES WHERE COURSE_ID = ?";
    private static final String SQL_UPDATE = "UPDATE COURSES SET URL = ? WHERE COURSE_ID = ?";
    private static final String SQL_DELETE_COURSE = "DELETE FROM COURSES WHERE COURSE_ID = ?";
    private static final String SQL_FIND_BY_NAME = "SELECT COURSE_ID " +
            "FROM COURSES WHERE TITLE = ?";

    @Override
    public Course findById(Integer courseId) throws ReviewsResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{courseId}, courseRowMapper);
        } catch(Exception e) {
            throw new ReviewsResourceNotFoundException("Course not found.");
        }
    }

    @Override
    public Integer create(String title, String url, String provider, String subject) throws ReviewsBadRequestException {
        try {
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, title);
                ps.setString(2, url);
                ps.setString(3, provider);
                ps.setString(4, subject);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("COURSE_ID");
            } catch (Exception e) {
            throw new ReviewsBadRequestException("Invalid request for course.");
        }
    }

    @Override
    public void update(Integer courseId, Course course) throws ReviewsBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{course.getUrl(), courseId});
        } catch(Exception e) {
            throw new ReviewsBadRequestException("Could not update course.");
        }
    }

    @Override
    public void removeById(Integer courseId) {
        jdbcTemplate.update(SQL_DELETE_COURSE, new Object[]{courseId});
    }

    private RowMapper<Course> courseRowMapper = ((rs, rowNum) -> {
        return new Course(rs.getInt("COURSE_ID"),
                rs.getString("TITLE"),
                rs.getString("URL"),
                rs.getString("PROVIDER"),
                rs.getString("SUBJECT"));
    });

    @Override
    public Integer getIdByName(String courseName) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, Integer.class, courseName);
    }
}
