package com.epam.webapp.dao;

import com.epam.webapp.dto.ClientDto;
import com.epam.webapp.entity.Entity;
import com.epam.webapp.exception.DaoException;

import java.util.Optional;

public interface ClientDao<Client extends Entity> extends Dao<Client> {

    Optional<Client> findClientByLoginAndPassword(String login, String password) throws DaoException;

    void addClient(ClientDto client) throws DaoException;

    void updateClientBalance(Long clientId, Object newValue) throws DaoException;

    void updateClientState(Long clientId, Object newValue) throws DaoException;

    Optional<Double> getActualBalance(Long clientId) throws DaoException;

}
