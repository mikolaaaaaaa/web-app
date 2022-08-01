package com.epam.webapp.entity;

import lombok.*;

import java.sql.Date;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Entity {

    private long clientId;
    private long trainerId;
    private Date date;
    private TrainerType trainerType;
    private double price;
    private boolean isPaid;
    private OrderState state;

    public static final String CLIENT_ID = "client_id";
    public static final String TRAINER_ID = "trainer_id";
    public static final String DATE = "date";
    public static final String TRAINER_TYPE = "trainer_type";
    public static final String PRICE = "price";
    public static final String IS_PAID = "is_paid";
    public static final String STATE = "state";
    public static final String TABLE = "order";




    public Order(long id, long clientId, long trainerId, Date date,
                 TrainerType trainerType, double price, boolean isPaid,OrderState state) {
        super(id);
        this.clientId = clientId;
        this.trainerId = trainerId;
        this.date = date;
        this.trainerType = trainerType;
        this.price = price;
        this.isPaid = isPaid;
        this.state = state;
    }

}
