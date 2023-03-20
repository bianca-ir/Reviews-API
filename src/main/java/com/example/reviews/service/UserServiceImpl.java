package com.example.reviews.service;

import com.example.reviews.exceptions.ReviewsAuthException;
import com.example.reviews.model.User;
import com.example.reviews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws ReviewsAuthException {
        if (email!= null)
            email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String firstName, String email, String password) throws ReviewsAuthException {
        if (email!= null)
            email = email.toLowerCase();
        // TODO: Email validation
        Integer userId = userRepository.create(firstName, email, password);
        return userRepository.findById(userId);
    }
}

