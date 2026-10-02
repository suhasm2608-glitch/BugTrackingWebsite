package com.bugtracking.bugtracking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String priority;

    private String status;

    private String reportedBy;

    private String assignedDeveloper;

    public Bug() {
    }

    public Bug(
            String title,
            String description,
            String priority,
            String status,
            String reportedBy,
            String assignedDeveloper) {

        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.reportedBy = reportedBy;
        this.assignedDeveloper = assignedDeveloper;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getAssignedDeveloper() {
        return assignedDeveloper;
    }

    public void setAssignedDeveloper(String assignedDeveloper) {
        this.assignedDeveloper = assignedDeveloper;
    }
}