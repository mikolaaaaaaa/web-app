package com.epam.webapp.exception;

public class ServletException extends Exception {
    public ServletException(Exception e) {
        super(e);
    }
    public ServletException(String message, Exception e) {
        super(message,e);
    }
    public ServletException(String message) {
        super(message);
    }
}
