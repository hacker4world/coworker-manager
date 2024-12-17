package com.hacker4world.coworkermanagement.enums;

public enum Status {
    NOT_COMPLETED("NOT_COMPLETED"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    CANCELED("CANCELED");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
