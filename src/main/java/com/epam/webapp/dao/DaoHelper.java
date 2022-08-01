package com.epam.webapp.dao;

import com.epam.webapp.connection.ProxyConnection;
import com.epam.webapp.dao.impl.*;
import com.epam.webapp.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {
    private final ProxyConnection connection;

    public DaoHelper(ProxyConnection connection) {
        this.connection = connection;
    }

    public ClientDaoImpl createClientDao() {
        return new ClientDaoImpl(connection);
    }

    public TrainerDaoImpl createTrainerDao() {
        return new TrainerDaoImpl(connection);
    }

    public ProgramDaoImpl createProgramDao() {
        return new ProgramDaoImpl(connection);
    }

    public ExerciseDaoImpl createExerciseDao() {
        return new ExerciseDaoImpl(connection);
    }

    public OrderDaoImpl createOrderDao() {
        return new OrderDaoImpl(connection);
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
