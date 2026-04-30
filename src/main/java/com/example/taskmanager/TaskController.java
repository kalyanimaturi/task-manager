package com.example.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    // GET all tasks
    @GetMapping
    public List<Task> getTasks() {
        return repository.findAll();
    }

    // POST new task
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return repository.save(task);
    }

    // GET tasks by user
    @GetMapping("/user/{username}")
    public List<Task> getTasksByUser(@PathVariable String username) {
        return repository.findByAssignedTo(username);
    }

    // DELETE task (Admin only)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id, @RequestParam String role) {
        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Only admin can delete");
        }
        repository.deleteById(id);
    }

    // GET tasks by project
    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProject(@PathVariable String projectId) {
        return repository.findByProjectId(projectId);
    }

    // UPDATE task status
    @PutMapping("/{id}")
    public Task update(@PathVariable String id,
                       @RequestBody Task updated,
                       @RequestParam String role) {

        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!role.equals("ADMIN") && !role.equals("MEMBER")) {
            throw new RuntimeException("Unauthorized");
        }

        task.setStatus(updated.getStatus());
        return repository.save(task);
    }

    // DASHBOARD
    @GetMapping("/dashboard")
    public Map<String, Long> getDashboard() {

        long total = repository.count();
        long pending = repository.countByStatus("PENDING");
        long done = repository.countByStatus("DONE");

        Map<String, Long> dashboard = new HashMap<>();
        dashboard.put("total", total);
        dashboard.put("pending", pending);
        dashboard.put("done", done);

        return dashboard;
    }
}