package com.bugtracking.bugtracking.repository;

import com.bugtracking.bugtracking.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBugIdOrderByCreatedAtDesc(Long bugId);
}