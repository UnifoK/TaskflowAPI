package io.github.unifok.taskflowapi.service;

import io.github.unifok.taskflowapi.model.Task;
import io.github.unifok.taskflowapi.model.User;
import io.github.unifok.taskflowapi.repository.TaskRepository;
import io.github.unifok.taskflowapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public Task createTaskForUser(Task task, Long userId) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (!currentUsername.equals(user.getUsername())) {
            throw new SecurityException("You do not have permission to create a task for this user.");
        }

        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByUserId(Long userId) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (!currentUsername.equals(user.getUsername())) {
            throw new SecurityException("You do not have permission to view these tasks.");
        }

        return taskRepository.findByUserId(userId);
    }

    public Task updateTask(Task taskDetails, Long taskId) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        if (!existingTask.getUser().getUsername().equals(currentUsername)) {
            throw new SecurityException("You do not have permission to update this task.");
        }

        existingTask.setTitle(taskDetails.getTitle());
        existingTask.setDescription(taskDetails.getDescription());
        existingTask.setCompleted(taskDetails.isCompleted());

        return taskRepository.save(existingTask);
    }

    public String deleteTask(Long taskId) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        if (!task.getUser().getUsername().equals(currentUsername)) {
            throw new SecurityException("User does not have permission to delete this task.");
        }

        taskRepository.deleteById(taskId);
        return "Task with id: " + taskId + " has been deleted.";
    }
}