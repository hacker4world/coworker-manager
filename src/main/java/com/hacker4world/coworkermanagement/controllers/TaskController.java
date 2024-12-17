package com.hacker4world.coworkermanagement.controllers;

import com.hacker4world.coworkermanagement.dtos.task.AssignTaskDto;
import com.hacker4world.coworkermanagement.dtos.ResponseDto;
import com.hacker4world.coworkermanagement.dtos.task.UpdateTaskStatus;
import com.hacker4world.coworkermanagement.entities.Task;
import com.hacker4world.coworkermanagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("assign")
    public ResponseDto<Void> assignTask(@RequestBody AssignTaskDto assignTaskDto) {
        return taskService.assignTaskToCoworker(assignTaskDto);
    }

    @PutMapping("update")
    public ResponseDto<Void> updateTask(@RequestBody UpdateTaskStatus updateTaskStatus) {
        return taskService.changeTaskStatus(updateTaskStatus);
    }

    @GetMapping("get/{coworkerId}")
    public ResponseDto<List<Task>> getCoworkerTasks(@PathVariable int coworkerId) {
        return taskService.getCoworkerTasks(coworkerId);
    }

}
