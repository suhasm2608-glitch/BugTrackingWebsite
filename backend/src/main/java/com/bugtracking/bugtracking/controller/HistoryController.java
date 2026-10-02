package com.bugtracking.bugtracking.controller;

import com.bugtracking.bugtracking.entity.History;
import com.bugtracking.bugtracking.repository.HistoryRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*")
public class HistoryController {

    private final HistoryRepository historyRepository;

    public HistoryController(
            HistoryRepository historyRepository) {

        this.historyRepository = historyRepository;
    }

    @PostMapping
    public ResponseEntity<History> addHistory(
            @RequestBody History history) {

        history.setTimestamp(LocalDateTime.now());

        History saved =
                historyRepository.save(history);

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<History>> getAllHistory() {

        return ResponseEntity.ok(
                historyRepository.findAll()
        );
    }

    @GetMapping("/bug/{bugId}")
    public ResponseEntity<List<History>> getBugHistory(
            @PathVariable Long bugId) {

        return ResponseEntity.ok(
                historyRepository
                        .findByBugIdOrderByTimestampDesc(bugId)
        );
    }
}