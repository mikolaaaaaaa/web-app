package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.AbstractDao;
import com.epam.webapp.dao.TrainerDao;
import com.epam.webapp.entity.Trainer;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.mapper.impl.ClientRowMapperImpl;
import com.epam.webapp.mapper.impl.TrainerRowMapperImpl;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class TrainerDaoImpl extends AbstractDao<Trainer> implements TrainerDao<Trainer> {

    private static final String SQL_SELECT_FIND_BY_LOGIN_AND_PASSWORD =
            "select * from trainer where login = ? and password = MD5(?)";
    private static final String SQL_SELECT_FIND_BY_LOGIN =
            "select * from trainer where login = ?";

    public TrainerDaoImpl(Connection connection) {
        super(connection, new TrainerRowMapperImpl());
    }

    @Override
    public String getTableName() {
        return Trainer.TABLE;
    }


    @Override
    public Optional<Trainer> findTrainerByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(
                SQL_SELECT_FIND_BY_LOGIN_AND_PASSWORD,
                login,
                password);
    }

    @Override
    public Optional<Trainer> findTrainerByLogin(String login) throws DaoException {
        return executeForSingleResult(
                SQL_SELECT_FIND_BY_LOGIN,
                login
        );
    }

    public List<Trainer> getAll() throws DaoException {
        List<Trainer> trainers = executeQuery("select * from `trainer`");
        return trainers;
    }
}
