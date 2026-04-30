package com.example.taskmanager;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByAssignedTo(String assignedTo);
    List<Task> findByProjectId(String projectId);
    long countByStatus(String status);
    
}
