package io.github.unifok.taskflowapi.controller;

import io.github.unifok.taskflowapi.model.Task;
import io.github.unifok.taskflowapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    // In your TaskController.java

    @PostMapping("/users/{userId}/tasks")
    public Task createTask(@PathVariable Long userId, @RequestBody Task task) {
        return taskService.createTaskForUser(task, userId);
    }

    @GetMapping("/users/{userId}/tasks")
    public List<Task> getTasks(@PathVariable Long userId) {
        return taskService.getTasksByUserId(userId);
    }

    @DeleteMapping("/tasks/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
         taskService.deleteTask(taskId);
         return "Task deleted, with id: " + taskId;
    }

    @PutMapping("/tasks/{taskID}")
    public Task updateTask(@PathVariable Long taskID, @RequestBody Task task) {
         return taskService.updateTask(task, taskID);
    }
}
