package com.epam.webapp.exception;

public class ServiceException extends Exception {

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServiceException(String message) {
        super(message);
    }

}
