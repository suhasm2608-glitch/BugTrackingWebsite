package com.bugtracking.bugtracking.controller;

import com.bugtracking.bugtracking.entity.Bug;
import com.bugtracking.bugtracking.repository.BugRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bugs")
@CrossOrigin(origins = "*")
public class BugController {

    private final BugRepository bugRepository;

    public BugController(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    // ================================
    // ADD BUG
    // ================================

    @PostMapping
    public ResponseEntity<Bug> addBug(@RequestBody Bug bug) {

        if (bug.getStatus() == null || bug.getStatus().isEmpty()) {
            bug.setStatus("OPEN");
        }

        Bug savedBug = bugRepository.save(bug);

        return ResponseEntity.ok(savedBug);
    }


    // ================================
    // GET ALL BUGS
    // ================================

    @GetMapping
    public ResponseEntity<List<Bug>> getAllBugs() {

        List<Bug> bugs = bugRepository.findAll();

        return ResponseEntity.ok(bugs);
    }


    // ================================
    // GET BUG BY ID
    // ================================

    @GetMapping("/{id}")
    public ResponseEntity<?> getBugById(
            @PathVariable Long id) {

        Optional<Bug> bug =
                bugRepository.findById(id);

        if (bug.isEmpty()) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(bug.get());
    }


    // ================================
    // UPDATE BUG
    // ================================

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBug(
            @PathVariable Long id,
            @RequestBody Bug updatedBug) {

        Optional<Bug> existingBug =
                bugRepository.findById(id);

        if (existingBug.isEmpty()) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        Bug bug = existingBug.get();

        bug.setTitle(
                updatedBug.getTitle()
        );

        bug.setDescription(
                updatedBug.getDescription()
        );

        bug.setPriority(
                updatedBug.getPriority()
        );

        bug.setStatus(
                updatedBug.getStatus()
        );

        bug.setReportedBy(
                updatedBug.getReportedBy()
        );

        bug.setAssignedDeveloper(
                updatedBug.getAssignedDeveloper()
        );

        Bug savedBug =
                bugRepository.save(bug);

        return ResponseEntity.ok(savedBug);
    }


    // ================================
    // DELETE BUG
    // ================================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBug(
            @PathVariable Long id) {

        Optional<Bug> bug =
                bugRepository.findById(id);

        if (bug.isEmpty()) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        bugRepository.deleteById(id);

        return ResponseEntity.ok(
                "Bug deleted successfully"
        );
    }

}