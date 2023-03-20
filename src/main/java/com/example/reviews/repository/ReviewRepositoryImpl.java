package com.example.reviews.repository;

import com.example.reviews.exceptions.ReviewsBadRequestException;
import com.example.reviews.exceptions.ReviewsResourceNotFoundException;
import com.example.reviews.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {


    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String SQL_CREATE = "INSERT INTO REVIEWS(REVIEW_ID, USER_ID, DESCRIPTION, COURSE_ID) VALUES (DEFAULT, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID = "SELECT REVIEW_ID, USER_ID, DESCRIPTION_ID, COURSE_ID FROM REVIEWS WHERE USER_ID = ? AND COURSE_ID = ? AND REVIEW_ID = ?";
    private static final String SQL_UPDATE = "UPDATE REVIEWS SET DESCRIPTION = ? WHERE USER_ID = ? AND COURSE_ID = ? AND REVIEW_ID = ?";
    private static final String SQL_DELETE = "DELETE FROM REVIEWS WHERE USER_ID = ? AND COURSE_ID = ? AND REVIEW_ID = ? ";
    private static final String SQL_FIND_ALL = "SELECT REVIEW_ID, USER_ID, DESCRIPTION_ID, COURSE_ID FROM REVIEWS WHERE USER_ID = ? AND COURSE_ID = ? ";
    @Override
    public List<Review> findAll(Integer userId, Integer courseId) throws ReviewsResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{userId, courseId}, reviewRowMapper);
    }

    @Override
    public Review findById(Integer userId, Integer courseId, Integer reviewId) throws ReviewsResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, courseId, reviewId}, reviewRowMapper);
        } catch (Exception e) {
            throw new ReviewsResourceNotFoundException("Could not find review.");
        }
    }

    @Override
    public Integer create(Integer userId, Integer courseId, String description) throws ReviewsBadRequestException {
        try {
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setInt(2, courseId);
                ps.setString(3, description);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("REVIEW_ID");

        } catch(Exception e) {
            throw new ReviewsBadRequestException("Could not create review.");

        }
    }

    @Override
    public void update(Integer userId, Integer reviewId, Integer courseId, Review review) throws ReviewsBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{review.getDescription(), userId, courseId, reviewId});
        }catch (Exception e) {
            throw new ReviewsBadRequestException("Could not update course");
        }

    }

    @Override
    public void removeById(Integer userId, Integer reviewId, Integer courseId) {
        int count = jdbcTemplate.update(SQL_DELETE, new Object[]{userId, courseId, reviewId});
        if(count == 0)
            throw new ReviewsResourceNotFoundException("Course could not be found");

    }

    private RowMapper<Review> reviewRowMapper = ((rs, rowNum) -> {
        return new Review(rs.getInt("REVIEW_ID"),
                rs.getInt("USER_ID"),
                rs.getInt("COURSE_ID"),
                rs.getString("DESCRIPTION"));

    });

}
