package com.epam.webapp.service;

import com.epam.webapp.dao.DaoHelper;
import com.epam.webapp.dao.DaoHelperFactory;
import com.epam.webapp.dao.impl.ClientDaoImpl;
import com.epam.webapp.dao.impl.OrderDaoImpl;
import com.epam.webapp.dao.impl.ProgramDaoImpl;
import com.epam.webapp.dto.ClientDto;
import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.Order;
import com.epam.webapp.entity.OrderState;
import com.epam.webapp.entity.Program;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClientService {

    private static final Logger LOGGER = LogManager.getLogger();

    public void registerClient(ClientDto client) throws ServiceException {
        try(DaoHelper daoHelper = DaoHelperFactory.create()) {
            ClientDaoImpl clientDao = daoHelper.createClientDao();
            clientDao.addClient(client);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Client> login(String login, String password) throws ServiceException {
        Optional<Client> client = null;
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            ClientDaoImpl clientDao = daoHelper.createClientDao();
            client = clientDao.findClientByLoginAndPassword(login, password);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return client;
    }

    public Optional<Client> getClientById(long clientId) {
        Optional<Client> client = null;
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            ClientDaoImpl clientDao = daoHelper.createClientDao();
            client = clientDao.findById(clientId);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    public void updateBalance(Client client, double newValue) throws ServiceException {
        client.setBalance(newValue);
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            ClientDaoImpl clientDao = daoHelper.createClientDao();
            clientDao.updateClientBalance(client.getId(), newValue);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public boolean hasOrder(long clientId) throws ServiceException {
        Optional<Program> program;
        Optional<Order> createdOrder;
        Optional<Order> processedOrder;
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            ProgramDaoImpl programDao = daoHelper.createProgramDao();
            OrderDaoImpl orderDao = daoHelper.createOrderDao();
            createdOrder = orderDao.findCreatedOrderById(clientId);
            processedOrder = orderDao.findProcessOrderById(clientId);
            program = programDao.findProcessProgramByClientId(clientId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return program.isPresent() || createdOrder.isPresent() || processedOrder.isPresent();
    }


}
