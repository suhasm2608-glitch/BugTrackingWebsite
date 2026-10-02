package com.bugtracking.bugtracking.repository;

import com.bugtracking.bugtracking.entity.History;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository
        extends JpaRepository<History, Long> {

    List<History> findByBugIdOrderByTimestampDesc(Long bugId);
}