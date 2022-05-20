package com.epam.webapp.entity;

public enum UserState {

    ACTIVE ("active"),
    DELETED ("deleted"),
    BLOCKED("blocked");

    private String state;

    UserState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
