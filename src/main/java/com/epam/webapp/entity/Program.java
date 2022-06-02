package com.epam.webapp.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Program extends Entity {

    private int orderId;
    private String startDate;
    private String endDate;
    private boolean hasDiet;
    private List<Exercise> exercises;
    private String feedback = "";
    private ProgramState programState;

    public static final String ORDER_ID = "order_id";
    public static final String START = "start";
    public static final String END = "end";
    public static final String HAS_DIET = "has_diet";
    public static final String FEEDBACK = "feedback";
    public static final String STATE = "state";
    public static final String TABLE = "program";

    public Program(long id, int orderId, String startDate, String endDate, boolean hasDiet, String feedback,ProgramState programState) {
        super(id);
        this.orderId = orderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hasDiet = hasDiet;
        this.feedback = feedback;
        this.programState = programState;
    }

}
