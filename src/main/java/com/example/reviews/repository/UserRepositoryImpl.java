package com.example.reviews.repository;

import com.example.reviews.exceptions.ReviewsAuthException;
import com.example.reviews.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {


    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String SQL_CREATE = "INSERT INTO USERS(USER_ID, FIRST_NAME, EMAIL, PASSWORD) VALUES(DEFAULT, ?, ?, ?)";

    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, FIRST_NAME, EMAIL, PASSWORD " +
            "FROM USERS WHERE USER_ID = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID, FIRST_NAME, EMAIL, PASSWORD " +
            "FROM USERS WHERE EMAIL = ?";
    private static final String SQL_FIND_BY_NAME = "SELECT USER_ID " +
            "FROM USERS WHERE FIRST_NAME = ?";



    @Override
    public Integer create(String firstName, String email, String password) {
        try {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, firstName);
            ps.setString(2, email);
            ps.setString(3, password);
            return ps;
        }, keyHolder);
        return (Integer) keyHolder.getKeys().get("USER_ID");
        } catch (Exception e) {
            throw new ReviewsAuthException("Could not create your account. ");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws ReviewsAuthException {
       try {
           User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, userRowMapper);
           if (!password.equals(user.getPassword()))
               throw new ReviewsAuthException("Invalid email or password.");
           return user;
       } catch(EmptyResultDataAccessException e) {
           throw new ReviewsAuthException("Invalid email or password. ");
       }
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("USER_ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"));
    });


    @Override
    public Integer getIdByName(String firstName) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, Integer.class, firstName);
    }
}
