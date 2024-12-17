package com.hacker4world.coworkermanagement.dtos.task;

public class AssignTaskDto {
    private String name;
    private String deadline;
    private String priority;
    private int coworkerId;

    public String getName() {
        return name;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getPriority() {
        return priority;
    }

    public int getCoworkerId() {
        return coworkerId;
    }
}
