package com.epam.webapp.dao;

import com.epam.webapp.dto.OrderDto;
import com.epam.webapp.entity.Order;
import com.epam.webapp.entity.OrderState;
import com.epam.webapp.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends Dao<Order> {

    void addOrder(OrderDto order) throws DaoException;

    Optional<Order> findOrderByClientId(long clientId) throws DaoException;

    List<Order> findOrderByTrainerId(long trainerId) throws DaoException;

    Optional<Order> findCreatedOrderById(long id) throws DaoException;

    Optional<Order> findProcessOrderById(long Id) throws DaoException;

    void updateState(long orderId, OrderState state) throws DaoException;
}
