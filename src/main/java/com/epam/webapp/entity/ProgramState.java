package com.epam.webapp.entity;

public enum ProgramState {
    PROCESS("process"),
    FINISHED("finished");

    private final String state;

    ProgramState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
