package com.example.reviews.controller;

import com.example.reviews.model.User;
import com.example.reviews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
// Login and Registration for users.
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;



    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
        String first_name = (String) userMap.get("firstName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
      User user = userService.registerUser(first_name, email, password);
      Map<String, String> map = new HashMap<>();
      map.put("status", "registration success");
      return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);
        Map<String, String> map = new HashMap<>();
        map.put("status", "login success");
        return new ResponseEntity<>(map, HttpStatus.OK);

    }
}
