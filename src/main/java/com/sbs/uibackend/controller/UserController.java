package com.sbs.uibackend.controller;

import com.sbs.uibackend.entity.User;
import com.sbs.uibackend.repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        User user1 = new User();
        user1.setUsername(user.getUsername());
        // Encode the password here!
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRole(user.getRole()); // or default role
        userRepository.save(user1);

        return ResponseEntity.ok("User registered successfully");
    }
}
