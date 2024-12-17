package com.hacker4world.coworkermanagement.entities;

import com.hacker4world.coworkermanagement.enums.Priority;
import com.hacker4world.coworkermanagement.enums.Status;
import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String deadline;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coworker_id")
    private Coworker coworker;

    public Task() {}

    public Task(String name, String deadline, Priority priority, Status status) {
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }


    public void setCoworker(Coworker coworker) {
        this.coworker = coworker;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
