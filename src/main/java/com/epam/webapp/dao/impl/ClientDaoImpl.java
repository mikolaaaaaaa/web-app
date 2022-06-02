package com.epam.webapp.dao.impl;

import com.epam.webapp.dao.AbstractDao;
import com.epam.webapp.dao.ClientDao;
import com.epam.webapp.dto.ClientDto;
import com.epam.webapp.dto.OrderDto;
import com.epam.webapp.entity.Client;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.mapper.impl.ClientRowMapperImpl;

import java.sql.Connection;
import java.util.Optional;

public class ClientDaoImpl extends AbstractDao<Client> implements ClientDao<Client> {

    private static final String SQL_INSERT_CLIENT =
            """
                    INSERT INTO `client`
                    (name,surname,login,password,type,gender,balance,state)
                    VALUE(?,?,?,MD5(?),?,?,?,?)
                    """;

    private static final String SQL_SELECT_FIND_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM client WHERE login = ? and PASSWORD = MD5(?)";

    private static final String SQL_SELECT_GET_ACTUAL_BALANCE = "SELECT `balance` FROM `client` WHERE id = ?";

    private static final String SQL_UPDATE_CLIENT_BALANCE = "UPDATE client SET balance = ? WHERE id = ?";

    private static final String SQL_UPDATE_CLIENT_STATE = "UPDATE client SET state = ? WHERE id = ?";

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
    public void addClient(ClientDto client) throws DaoException {
            executeUpdate(
                    SQL_INSERT_CLIENT,
                    client.getName(),
                    client.getSurname(),
                    client.getLogin(),
                    client.getPassword(),
                    client.getType().toString(),
                    client.getGender().toString(),
                    client.getBalance(),
                    client.getState().toString()
            );
    }

    @Override
    public void updateClientBalance(Long clientId, Object newValue) throws DaoException {
        update(SQL_UPDATE_CLIENT_BALANCE, newValue, clientId);
    }

    @Override
    public void updateClientState(Long clientId, Object newValue) throws DaoException {
        update(SQL_UPDATE_CLIENT_STATE,newValue.toString(),clientId);
    }

    @Override
    public Optional<Double> getActualBalance(Long clientId) throws DaoException {
        return Optional.empty();
    }

    @Override
    public String getTableName() {
        return Client.TABLE;
    }

}
