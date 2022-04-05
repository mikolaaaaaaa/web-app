package com.epam.webapp.service;

import com.epam.webapp.util.PropertiesLoader;

import java.lang.constant.Constable;
import java.text.DecimalFormat;
import java.util.Properties;

public class OrderService {

    private static final String DATE_PATTERN = "[0-1]{2}-[0-9]{2}-[0-9]{4}";
    private static final String PRICE_PROPERTIES_PATH="price.properties";

    public boolean checkInputDate(String date) {
        return date.matches(DATE_PATTERN);
    }

    public String calculateCoast(int trainingCount, boolean trainerIsPersonal) {
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
        return new DecimalFormat("#.##").format(price);
    }

}
