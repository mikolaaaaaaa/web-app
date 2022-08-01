package com.epam.webapp.service;

import com.epam.webapp.connection.ConnectionPool;
import com.epam.webapp.dao.DaoHelper;
import com.epam.webapp.dao.DaoHelperFactory;
import com.epam.webapp.dao.TrainerDao;
import com.epam.webapp.dao.impl.ClientDaoImpl;
import com.epam.webapp.dao.impl.TrainerDaoImpl;
import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.Trainer;
import com.epam.webapp.entity.UserState;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class TrainerService {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final Long TRAINER_NOT_FOUND = Long.valueOf(-1);
    private static final int MIN_TRAINER_COUNT = 1;

    public Optional<Trainer> login(String login, String password) throws ServiceException {
        Optional<Trainer> trainer = null;
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            TrainerDaoImpl trainerDao = daoHelper.createTrainerDao();
            trainer = trainerDao.findTrainerByLoginAndPassword(login, password);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return trainer;
    }

    public Long findTrainerForClient() throws ServiceException {
        List<Long> trainerIdList;
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            TrainerDaoImpl trainerDao = daoHelper.createTrainerDao();
            trainerIdList = trainerDao.getTrainerIdList();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        if (trainerIdList.isEmpty()) {
            return (long) -1;
        } else {
            Random random = new Random();
            return trainerIdList.get(random.nextInt(trainerIdList.size()));
        }
    }

    public Optional<Trainer> showTrainerInfo(String login) throws ServiceException {
        Optional<Trainer> trainer = null;
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            TrainerDaoImpl trainerDao = daoHelper.createTrainerDao();
            trainer = trainerDao.findTrainerByLogin(login);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
        return trainer;
    }

    public List<Client> showClientList() throws ServiceException {
        List<Client> clients = new ArrayList<>();
        try(DaoHelper daoHelper = DaoHelperFactory.create()) {
            ClientDaoImpl clientDao = daoHelper.createClientDao();
            clients = clientDao.findAll();
            clients = clients.stream().filter(o -> o.getState() != UserState.DELETED).toList();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return clients;
    }

    public void blockClient(long clientId, UserState state) throws ServiceException {
        try(DaoHelper daoHelper = DaoHelperFactory.create()) {
            ClientDaoImpl clientDao = daoHelper.createClientDao();
            clientDao.updateClientState(clientId,state);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void unblockClient(long clientId, UserState state) throws ServiceException {
        try(DaoHelper daoHelper = DaoHelperFactory.create()) {
            ClientDaoImpl clientDao = daoHelper.createClientDao();
            clientDao.updateClientState(clientId,state);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteClient(long clientId, UserState state) throws ServiceException {
        try(DaoHelper daoHelper = DaoHelperFactory.create()) {
            ClientDaoImpl clientDao = daoHelper.createClientDao();
            clientDao.updateClientState(clientId,state);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void restoreClient(long clientId, UserState state) throws ServiceException {
        try(DaoHelper daoHelper = DaoHelperFactory.create()) {
            ClientDaoImpl clientDao = daoHelper.createClientDao();
            clientDao.updateClientState(clientId,state);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
