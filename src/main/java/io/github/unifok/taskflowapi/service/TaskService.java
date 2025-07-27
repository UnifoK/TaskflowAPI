package io.github.unifok.taskflowapi.service;

import io.github.unifok.taskflowapi.model.Task;
import io.github.unifok.taskflowapi.model.User;
import io.github.unifok.taskflowapi.repository.TaskRepository;
import io.github.unifok.taskflowapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public Task createTaskForUser(Task task, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        return taskRepository.save(task);
    }
    public List<Task> getTasksByUserId(long id) {
        return taskRepository.findByUserId(id);
    }
    public Task updateTask(Task task,long id) {
        Task temp=taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id: " + id));;
        temp.setCompleted(task.isCompleted());
        temp.setDescription(task.getDescription());
        temp.setUser(task.getUser());
        temp.setTitle(task.getTitle());
        return taskRepository.save(temp);
    }

    public String deleteTask(long id) {
        taskRepository.deleteById(id);
        return "Task with id: " + id + " deleted";
    }
}
