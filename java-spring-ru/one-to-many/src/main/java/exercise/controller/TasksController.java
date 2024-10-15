package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserRepository userRepository;
    // BEGIN
    @GetMapping
    public List<TaskDTO> getTasks() {
        var tasks = taskRepository.findAll();
        return tasks.stream().map(taskMapper::map).toList();
    }

    @GetMapping("/{id}")
    public TaskDTO getTask(@PathVariable Long id) {
        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id" + id + " not found!"));
        return taskMapper.map(task);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createTask(@Valid @RequestBody TaskCreateDTO taskData) {
        var task = taskMapper.map(taskData);
        var newTask = taskRepository.save(task);
        return taskMapper.map(newTask);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@Valid @PathVariable Long id, @RequestBody TaskUpdateDTO taskData) {
        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id" + id + " not found!"));
        var user = userRepository.findById(taskData.getAssigneeId()).get();
        task.setAssignee(user);
        taskMapper.update(taskData, task);
        taskRepository.save(task);
        return taskMapper.map(task);
    }
    // END
}