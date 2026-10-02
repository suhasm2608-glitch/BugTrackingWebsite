package com.bugtracking.bugtracking.controller;

import com.bugtracking.bugtracking.entity.User;
import com.bugtracking.bugtracking.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {

        Optional<User> user = userRepository.findByUsername(
                loginUser.getUsername()
        );

        if (user.isEmpty()) {
            return ResponseEntity
                    .status(401)
                    .body("Invalid username or password");
        }

        User existingUser = user.get();

        if (!existingUser.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity
                    .status(401)
                    .body("Invalid username or password");
        }

        if (!existingUser.getRole().equalsIgnoreCase(loginUser.getRole())) {
            return ResponseEntity
                    .status(401)
                    .body("Incorrect role selected");
        }

        return ResponseEntity.ok(existingUser);
    }
}