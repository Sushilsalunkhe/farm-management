package com.sbs.uibackend.auth;

import com.sbs.uibackend.component.JwtUtil;
import com.sbs.uibackend.entity.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        // TEMP: Replace later with DB validation
        if ("admin".equals(req.getUsername()) &&
                "admin".equals(req.getPassword())) {

            String token = jwtUtil.generateToken(req.getUsername());
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid credentials");
    }
}

