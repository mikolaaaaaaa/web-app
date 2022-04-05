package com.epam.webapp.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Program extends Entity {

    private int orderId;
    private String startDate;
    private String endDate;
    private boolean hasDiet;
    private List<Exercise> exercises = new ArrayList<>();
    private String feedback = "";
    private ProgramState programState;

    public static final String ORDER_ID = "order_id";
    public static final String START = "start";
    public static final String END = "end";
    public static final String HAS_DIET = "has_diet";
    public static final String FEEDBACK = "feedback";
    public static final String STATE = "state";

    public Program(long id, int orderId, String startDate, String endDate, boolean hasDiet, String feedback,ProgramState programState) {
        super(id);
        this.orderId = orderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hasDiet = hasDiet;
        this.feedback = feedback;
        this.programState = programState;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isHasDiet() {
        return hasDiet;
    }

    public void setHasDiet(boolean hasDiet) {
        this.hasDiet = hasDiet;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public ProgramState getProgramState() {
        return programState;
    }

    public void setProgramState(ProgramState programState) {
        this.programState = programState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Program program = (Program) o;
        return orderId == program.orderId &&
                hasDiet == program.hasDiet &&
                Objects.equals(startDate, program.startDate) &&
                Objects.equals(endDate, program.endDate) &&
                Objects.equals(feedback, program.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId,
                startDate,
                endDate,
                hasDiet,
                feedback
        );
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Program{")
                .append("id=").append(id)
                .append(", orderId=").append(orderId)
                .append(", startDate='").append(startDate).append('\'')
                .append(", endDate='").append(endDate).append('\'')
                .append(", hasDiet=").append(hasDiet)
                .append(", feedback='").append(feedback).append('\'')
                .append(", exercises'").append(exercises)
                .append(", programState'").append(programState)
                .append('}')
                .toString();
    }
}
