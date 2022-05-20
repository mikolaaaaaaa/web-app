package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.AbstractDao;
import com.epam.webapp.dao.OrderDao;
import com.epam.webapp.dto.OrderDto;
import com.epam.webapp.entity.Order;
import com.epam.webapp.entity.OrderState;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.mapper.RowMapper;
import com.epam.webapp.mapper.impl.OrderRowMapperImpl;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    private static final String SQL_INSERT_ORDER =
            """
                    INSERT INTO `order`
                    (client_id,trainer_id,date,trainer_type,price,is_paid,state)
                    VALUE(?,?,?,?,?,?,?)
                    """;
    private static final String SQL_SELECT_FIND_ORDER_BY_CLIENT_ID =
            "SELECT * FROM `order` WHERE client_id = ?";
    private static final String SQL_SELECT_FIND_ORDER_BY_TRAINER_ID =
            "SELECT * FROM `order` WHERE trainer_id = ?";
    private static final String SQL_SELECT_FIND_ORDER_BY_CLIENT_ID_AND_STATE =
            "SELECT * FROM `order` WHERE client_id = ? AND state = ?";
    private static final String SQL_UPDATE_STATE =
            """
                    UPDATE `order`
                    SET `state` = ?
                    WHERE `id` = ?
                    """;

    public OrderDaoImpl(Connection connection) {
        super(connection, new OrderRowMapperImpl());
    }

    @Override
    public String getTableName() {
        return Order.TABLE;
    }

    @Override
    public void save(Order item) throws DaoException {

    }

    @Override
    public void addOrder(OrderDto order) throws DaoException {
        executeUpdate(
                SQL_INSERT_ORDER,
                order.getClientId(),
                order.getTrainerId(),
                order.getDate(),
                order.getTrainerType().toString(),
                order.getPrice(),
                order.isPaid(),
                order.getState().toString());
    }

    @Override
    public Optional<Order> findOrderByClientId(long clientId) throws DaoException {
        return executeForSingleResult(SQL_SELECT_FIND_ORDER_BY_CLIENT_ID, clientId);
    }

    @Override
    public List<Order> findOrderByTrainerId(long trainerId) throws DaoException {
        return executeQuery(SQL_SELECT_FIND_ORDER_BY_TRAINER_ID, trainerId);
    }

    @Override
    public Optional<Order> findCreatedOrderById(long id) throws DaoException {
        return executeForSingleResult(SQL_SELECT_FIND_ORDER_BY_CLIENT_ID_AND_STATE, id, OrderState.CREATED.toString());
    }

    @Override
    public Optional<Order> findProcessOrderById(long id) throws DaoException {
        return executeForSingleResult(SQL_SELECT_FIND_ORDER_BY_CLIENT_ID_AND_STATE, id, OrderState.PROCESSED.toString());
    }

    @Override
    public void updateState(long orderId, OrderState state) throws DaoException {
         executeUpdate(SQL_UPDATE_STATE, state, orderId);
    }

}
