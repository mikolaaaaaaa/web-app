package com.epam.webapp.service;

import com.epam.webapp.dao.DaoHelper;
import com.epam.webapp.dao.DaoHelperFactory;
import com.epam.webapp.dao.impl.ClientDaoImpl;
import com.epam.webapp.entity.Client;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    private static final Logger LOGGER = LogManager.getLogger();

    public Optional<Client> login(String login, String password) throws ServiceException {
       Optional<Client> client = null;
        try (DaoHelper daoHelper = DaoHelperFactory.create()){
           ClientDaoImpl clientDao = daoHelper.createClientDao();
           client = clientDao.findClientByLoginAndPassword(login,password);
       } catch (DaoException | SQLException e) {
           throw new ServiceException(e);
       }
        return client;
    }

}
