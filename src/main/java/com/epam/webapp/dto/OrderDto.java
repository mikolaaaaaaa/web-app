package com.epam.webapp.dto;

import com.epam.webapp.entity.OrderState;
import com.epam.webapp.entity.TrainerType;
import lombok.Data;

import java.sql.Date;

@Data
public class OrderDto {

    private Long clientId;
    private Long trainerId;
    private Date date;
    private TrainerType trainerType;
    private double price;
    private boolean isPaid;
    private OrderState state;

    public OrderDto(Long clientId, Long trainerId, Date date, TrainerType trainerType,
                    double price, boolean isPaid, OrderState state) {
        this.clientId = clientId;
        this.trainerId = trainerId;
        this.date = date;
        this.trainerType = trainerType;
        this.price = price;
        this.isPaid = isPaid;
        this.state = state;
    }


}
