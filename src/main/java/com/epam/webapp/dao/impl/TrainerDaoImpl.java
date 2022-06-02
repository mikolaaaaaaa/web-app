package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.AbstractDao;
import com.epam.webapp.dao.TrainerDao;
import com.epam.webapp.entity.Entity;
import com.epam.webapp.entity.Trainer;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.mapper.impl.ClientRowMapperImpl;
import com.epam.webapp.mapper.impl.TrainerRowMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrainerDaoImpl extends AbstractDao<Trainer> implements TrainerDao<Trainer> {

    private static final String SQL_SELECT_FIND_BY_LOGIN_AND_PASSWORD =
            "select * from trainer where login = ? and password = MD5(?)";
    private static final String SQL_SELECT_FIND_BY_LOGIN =
            "select * from trainer where login = ?";
    private static final String SQL_SELECT_GET_ALL_TRAINER_ID = "SELECT `id` FROM `trainer`";

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

    @Override
    public List<Long> getTrainerIdList() throws DaoException {
        List<Long> trainerIdList = new ArrayList<>();
        try {
            PreparedStatement statement = createStatement(SQL_SELECT_GET_ALL_TRAINER_ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                trainerIdList.add(resultSet.getLong(Entity.ID));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return trainerIdList;
    }

    public List<Trainer> getAll() throws DaoException {
        return executeQuery("select * from `trainer`");
    }
}
