package com.bugtracking.bugtracking.controller;

import com.bugtracking.bugtracking.entity.User;
import com.bugtracking.bugtracking.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ==========================================
    // GET ALL DEVELOPERS
    // ==========================================

    @GetMapping("/developers")
    public ResponseEntity<List<User>> getDevelopers() {

        List<User> developers =
                userRepository.findAll()
                        .stream()
                        .filter(user ->
                                user.getRole() != null &&
                                user.getRole()
                                    .equalsIgnoreCase("DEVELOPER")
                        )
                        .toList();

        return ResponseEntity.ok(developers);
    }
}