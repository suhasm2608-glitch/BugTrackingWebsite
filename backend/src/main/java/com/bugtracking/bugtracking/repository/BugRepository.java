package com.bugtracking.bugtracking.repository;

import com.bugtracking.bugtracking.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug, Long> {
}