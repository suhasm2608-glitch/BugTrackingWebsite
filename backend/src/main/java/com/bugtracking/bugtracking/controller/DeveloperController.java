package com.bugtracking.bugtracking.controller;

import com.bugtracking.bugtracking.entity.User;
import com.bugtracking.bugtracking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/developers")
@CrossOrigin(origins = "*")
public class DeveloperController {

    @Autowired
    private UserRepository userRepository;

    // Get all developers
    @GetMapping
    public ResponseEntity<List<User>> getDevelopers() {

        List<User> developers = userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() != null
                        && user.getRole().equalsIgnoreCase("DEVELOPER"))
                .collect(Collectors.toList());

        return ResponseEntity.ok(developers);
    }

    // Add a new developer
    @PostMapping
    public ResponseEntity<?> addDeveloper(@RequestBody User user) {

        // Check username
        if (user.getUsername() == null ||
                user.getUsername().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body("Username is required");
        }

        // Check password
        if (user.getPassword() == null ||
                user.getPassword().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body("Password is required");
        }

        // Check whether username already exists
        boolean usernameExists = userRepository.findAll()
                .stream()
                .anyMatch(existingUser ->
                        existingUser.getUsername() != null &&
                        existingUser.getUsername()
                                .equalsIgnoreCase(user.getUsername()));

        if (usernameExists) {
            return ResponseEntity.badRequest()
                    .body("Username already exists");
        }

        // Automatically assign DEVELOPER role
        user.setRole("DEVELOPER");

        // Save developer
        User savedDeveloper = userRepository.save(user);

        return ResponseEntity.ok(savedDeveloper);
    }
}