package com.hacker4world.coworkermanagement.services;

import com.hacker4world.coworkermanagement.dtos.task.UpdateTaskStatus;
import com.hacker4world.coworkermanagement.enums.Priority;
import com.hacker4world.coworkermanagement.dtos.task.AssignTaskDto;
import com.hacker4world.coworkermanagement.dtos.ResponseDto;
import com.hacker4world.coworkermanagement.entities.Coworker;
import com.hacker4world.coworkermanagement.entities.Task;
import com.hacker4world.coworkermanagement.enums.Status;
import com.hacker4world.coworkermanagement.repositories.CoworkerRepository;
import com.hacker4world.coworkermanagement.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CoworkerRepository coworkerRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, CoworkerRepository coworkerRepository) {
        this.taskRepository = taskRepository;
        this.coworkerRepository = coworkerRepository;
    }

    public ResponseDto<Void> assignTaskToCoworker(AssignTaskDto taskData) {
        Optional<Coworker> existingCoworker = coworkerRepository.findById(taskData.getCoworkerId());
        if (existingCoworker.isEmpty()) return new ResponseDto<>(404, "A coworker with the given id does not exist");

        Coworker coworker = existingCoworker.get();

        try {
            Task newTask = new Task(
                    taskData.getName(),
                    taskData.getDeadline(),
                    Priority.valueOf(taskData.getPriority().toUpperCase()),
                    Status.NOT_COMPLETED
            );

            newTask.setCoworker(coworker);

            taskRepository.save(newTask);

        } catch (IllegalArgumentException e) {
            return new ResponseDto<>(400, "Invalid priority value");
        }

        return new ResponseDto<>(200, "Task assigned successfully");
    }

    public ResponseDto<Void> changeTaskStatus(UpdateTaskStatus updateTaskStatus) {
        Optional<Task> existingTask = taskRepository.findById(updateTaskStatus.getTaskId());

        if (existingTask.isEmpty()) return new ResponseDto<>(404, "A task with the given id does not exist");

        Task task = existingTask.get();

        try {
            task.setStatus(Status.valueOf(updateTaskStatus.getTaskStatus().toUpperCase()));
            taskRepository.save(task);

            return new ResponseDto<>(200, "Task updated successfully");
        } catch (IllegalArgumentException e) {
            return new ResponseDto<>(400, "Invalid status value");
        }
    }

    public ResponseDto<List<Task>> getCoworkerTasks(int coworkerId) {
        Optional<Coworker> existingCoworker = coworkerRepository.findById(coworkerId);

        if (existingCoworker.isEmpty()) return new ResponseDto<>(404, "No coworker with the given id exists");

        return new ResponseDto<>(200, "Coworker tasks", existingCoworker.get().getTasks());
    }

}
