package com.hacker4world.coworkermanagement.repositories;

import com.hacker4world.coworkermanagement.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
