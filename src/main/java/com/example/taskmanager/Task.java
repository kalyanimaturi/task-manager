package com.example.taskmanager;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {

    @Id
    private String id;

    private String title;
    private String assignedTo;
    private String status;
    private String projectId;

    public Task() {}

    public Task(String title, String assignedTo, String status) {
        this.title = title;
        this.assignedTo = assignedTo;
        this.status = status;
    }

    public String getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}