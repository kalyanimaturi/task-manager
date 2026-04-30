package com.example.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository repository;

    // Create project
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return repository.save(project);
    }

    // Get all projects
    @GetMapping
    public List<Project> getProjects() {
        return repository.findAll();
    }
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable String id) {
        repository.deleteById(id);
    }
}