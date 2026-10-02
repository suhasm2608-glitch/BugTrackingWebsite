package com.bugtracking.bugtracking.controller;

import com.bugtracking.bugtracking.entity.Comment;
import com.bugtracking.bugtracking.repository.CommentRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(
            @RequestBody Comment comment) {

        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment =
                commentRepository.save(comment);

        return ResponseEntity.ok(savedComment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {

        return ResponseEntity.ok(
                commentRepository.findAll()
        );
    }

    @GetMapping("/bug/{bugId}")
    public ResponseEntity<List<Comment>> getBugComments(
            @PathVariable Long bugId) {

        return ResponseEntity.ok(
                commentRepository
                        .findByBugIdOrderByCreatedAtDesc(bugId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long id) {

        if (!commentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        commentRepository.deleteById(id);

        return ResponseEntity.ok(
                "Comment deleted successfully"
        );
    }
}