package com.hacker4world.coworkermanagement.enums;

public enum Priority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String value;

    Priority(String value) {
        this.value = value;
    }

    public String getPriority() {
        return value;
    }
}
