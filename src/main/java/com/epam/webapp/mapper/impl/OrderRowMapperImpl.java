package com.epam.webapp.mapper.impl;

import com.epam.webapp.entity.Order;
import com.epam.webapp.entity.OrderState;
import com.epam.webapp.entity.TrainerType;
import com.epam.webapp.mapper.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class OrderRowMapperImpl implements RowMapper<Order> {

    @Override
    public Order map(ResultSet resultSet) throws SQLException {

        long id = Long.parseLong(resultSet.getString(Order.ID));
        long clientId = Long.parseLong(resultSet.getString(Order.CLIENT_ID));
        long trainerId = Long.parseLong(resultSet.getString(Order.TRAINER_ID));
        Date date = Date.valueOf(resultSet.getString(Order.DATE));
        TrainerType trainerType = TrainerType.valueOf(resultSet
                .getString(Order.TRAINER_TYPE)
                .toUpperCase()
        );
        double price = Double.parseDouble(resultSet.getString(Order.PRICE));
        boolean isPaid = Boolean.parseBoolean(resultSet.getString(Order.IS_PAID));
        String state = resultSet.getString(Order.STATE);
        OrderState orderState = OrderState.valueOf(state.toUpperCase());

        return new Order(
                id,
                clientId,
                trainerId,
                date,
                trainerType,
                price,
                isPaid,
                orderState
        );
    }
}
