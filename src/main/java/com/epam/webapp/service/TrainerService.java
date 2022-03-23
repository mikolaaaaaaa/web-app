package com.epam.webapp.service;

import com.epam.webapp.connection.ConnectionPool;
import com.epam.webapp.dao.DaoHelper;
import com.epam.webapp.dao.DaoHelperFactory;
import com.epam.webapp.dao.TrainerDao;
import com.epam.webapp.dao.impl.ClientDaoImpl;
import com.epam.webapp.dao.impl.TrainerDaoImpl;
import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.Trainer;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Optional;

public class TrainerService {
    private static final Logger LOGGER = LogManager.getLogger();

    public Optional<Trainer> login(String login, String password) throws ServiceException {
        Optional<Trainer> trainer = null;
        try (DaoHelper daoHelper = DaoHelperFactory.create()){
            TrainerDaoImpl trainerDao = daoHelper.createTrainerDao();
            trainer = trainerDao.findTrainerByLoginAndPassword(login,password);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return trainer;
    }

    public Optional<Trainer> showTrainerInfo(String login) throws ServiceException {
        Optional<Trainer> trainer = null;
        try(DaoHelper daoHelper = DaoHelperFactory.create()) {
            TrainerDaoImpl trainerDao = daoHelper.createTrainerDao();
            trainer = trainerDao.findTrainerByLogin(login);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
        return trainer;
    }
}
