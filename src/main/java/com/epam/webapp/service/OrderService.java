package com.epam.webapp.service;

import com.epam.webapp.dao.DaoHelper;
import com.epam.webapp.dao.DaoHelperFactory;
import com.epam.webapp.dao.impl.OrderDaoImpl;
import com.epam.webapp.dto.OrderDto;
import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.Order;
import com.epam.webapp.exception.DaoException;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderService {

    private static final String DATE_PATTERN = "[0-1]{2}-[0-9]{2}-[0-9]{4}";
    private static final String PRICE_PROPERTIES_PATH = "price.properties";
    private static final Logger LOGGER = LogManager.getLogger();

    public boolean checkInputDate(String date) {
        return date.matches(DATE_PATTERN);
    }

    public double calculatePrice(int trainingCount, boolean trainerIsPersonal) {
        Properties price_properties = PropertiesLoader.load(PRICE_PROPERTIES_PATH);
        double price = 0;
        double trainingPrice = Double.parseDouble(price_properties.getProperty("training"));
        price += trainingPrice * trainingCount;
        if (trainerIsPersonal) {
            double personalTrainerPrice = Double.parseDouble(price_properties.getProperty("personal_trainer"));
            price += personalTrainerPrice;
        } else {
            double usualTrainerPrice = Double.parseDouble(price_properties.getProperty("usual_trainer"));
            price += usualTrainerPrice;
        }
        return price;
    }

    public void createOrder(OrderDto order, Client client) throws ServiceException {
        try (DaoHelper daoHelper = DaoHelperFactory.create()) {
            OrderDaoImpl orderDao = daoHelper.createOrderDao();
            daoHelper.startTransaction();
            pay(client,order.getPrice());
            orderDao.addOrder(order);
            daoHelper.endTransaction();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void pay(Client client, double orderPrice) throws ServiceException {
        ClientService service = new ClientService();
        service.updateBalance(client, client.getBalance() - orderPrice);
    }

    public boolean isPayable(Client client, double orderPrice) {
        double clientBalance = client.getBalance();
        return clientBalance >= orderPrice;
    }

    public List<Order> findOrdersByTrainerId(long trainerId) throws ServiceException {
        List<Order> orders = new ArrayList<>();
        try(DaoHelper daoHelper = DaoHelperFactory.create()) {
            OrderDaoImpl orderDao = daoHelper.createOrderDao();
            orders = orderDao.findOrderByTrainerId(trainerId);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
