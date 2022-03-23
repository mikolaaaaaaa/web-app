package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.AbstractDao;
import com.epam.webapp.dao.ClientDao;
import com.epam.webapp.entity.Client;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.mapper.impl.ClientRowMapperImpl;

import java.sql.Connection;
import java.util.Optional;

public class ClientDaoImpl extends AbstractDao<Client> implements ClientDao<Client> {
    private static final String SQL_SELECT_FIND_BY_LOGIN_AND_PASSWORD =
            "select * from client where login = ? and password = MD5(?)";

    public ClientDaoImpl(Connection connection) {
        super(connection, new ClientRowMapperImpl());
    }

    @Override
    public Optional<Client> findClientByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(
                SQL_SELECT_FIND_BY_LOGIN_AND_PASSWORD,
                login,
                password
        );
    }

    @Override
    public String getTableName() {
        return Client.TABLE;
    }

}
