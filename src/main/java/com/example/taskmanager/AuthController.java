package com.example.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository repo;
    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        user.setRole("MEMBER"); // default role
        return repo.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User existing = repo.findByUsername(user.getUsername());

        if (existing != null && existing.getPassword().equals(user.getPassword())) {
            return JwtUtil.generateToken(user.getUsername());
        }

        throw new RuntimeException("Invalid credentials");
    }
}