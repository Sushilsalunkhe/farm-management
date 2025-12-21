package com.sbs.uibackend.auth;

import com.sbs.uibackend.component.JwtUtil;
import com.sbs.uibackend.constants.DevAdminConstants;
import com.sbs.uibackend.dto.AuthResponse;
import com.sbs.uibackend.dto.LoginRequest;
import com.sbs.uibackend.dto.RegisterRequest;
import com.sbs.uibackend.entity.User;
import com.sbs.uibackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {

        if (userRepository.findByUsernameIgnoreCase(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        System.out.println("req value" + req.getPassword());
        /*if(req.getRole().equalsIgnoreCase("ADMIN") ) {
            user.setUsername("Yatharth");
            user.setPassword(passwordEncoder.encode("2025"));
            user.setRole("ADMIN");
        } else {*/
        user.setRole("USER");

        System.out.println("User value" + user.getUsername());
        System.out.println("User value" + user.getPassword());
        System.out.println("User value" + user.getRole());

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(
                new AuthResponse(token, user.getUsername(), user.getRole())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        /* ================= TEMP ADMIN LOGIN ================= */
        if (DevAdminConstants.ADMIN_USERNAME.equals(req.getUsername())
                && DevAdminConstants.ADMIN_PASSWORD.equals(req.getPassword())) {

            String token = jwtUtil.generateToken(
                    DevAdminConstants.ADMIN_USERNAME,
                    DevAdminConstants.ADMIN_ROLE
            );

            return ResponseEntity.ok(
                    new AuthResponse(token,
                            DevAdminConstants.ADMIN_USERNAME,
                            DevAdminConstants.ADMIN_ROLE)
            );
        }

        /* ================= NORMAL USER LOGIN ================= */
        User user = userRepository
                .findByUsernameIgnoreCase(req.getUsername())
                .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(
                new AuthResponse(token, user.getUsername(), user.getRole())
        );
    }

}
