package com.epam.webapp.service;

import com.epam.webapp.util.PropertiesLoader;
import org.junit.jupiter.api.Test;

import java.util.Properties;

public class OrderServiceTest {

    private static final OrderService service = new OrderService();

    @Test
    public void testCalculatePrice() {
//        Properties properties = PropertiesLoader.load("price.properties");
//        System.out.println(properties.getProperty("usual_trainer"));
        String price = service.calculateCoast(4,false);
        System.out.println(price);
    }
}
