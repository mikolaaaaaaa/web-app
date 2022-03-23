package com.epam.webapp.service;

public class OrderService {
    private static final String DATE_PATTERN = "[0-1]{2}-[0-9]{2}-[0-9]{4}";

    public boolean checkInputDate(String date) {
        return date.matches(DATE_PATTERN);
    }

}
