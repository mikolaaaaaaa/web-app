package com.epam.webapp.dao;

import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.Entity;
import com.epam.webapp.exception.DaoException;

import java.util.Optional;

public interface ClientDao<Client extends Entity> extends Dao<Client> {
    Optional<Client> findClientByLoginAndPassword(String login, String password) throws DaoException;
}
