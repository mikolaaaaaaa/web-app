package com.epam.webapp.command.common;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageChangeCommand implements Command {

    private static final String MAIN_PAGE_KEY = "main.jsp";
    private static final String MAIN_PAGE_VALUE = "WEB-INF/jsp/client/main.jsp";
    private static final String LOGIN_PAGE_KEY = "login.jsp";
    private static final String LOGIN_PAGE_VALUE = "WEB-INF/jsp/login.jsp";
    private static final String HISTORY_PAGE_KEY = "history.jsp";
    private static final String HISTORY_PAGE_VALUE = "WEB-INF/jsp/client/history.jsp";
    private static final String ORDER_PAGE_KEY = "order.jsp";
    private static final String ORDER_PAGE_VALUE = "WEB-INF/jsp/client/order.jsp";
    private static final String PROGRAM_PAGE_KEY = "program.jsp";
    private static final String PROGRAM_PAGE_VALUE = "WEB-INF/jsp/client/program.jsp";
    private static final String LOCALE_ATTRIBUTE_NAME = "locale";
    private static final String CURRENT_PAGE_ATTRIBUTE_NAME = "currentPage";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String newLanguage = req.getParameter(LOCALE_ATTRIBUTE_NAME);
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute(LOCALE_ATTRIBUTE_NAME,newLanguage);
        String currentPage = (String) httpSession.getAttribute(CURRENT_PAGE_ATTRIBUTE_NAME);
        String currentResultPage;
        switch (currentPage) {
            case MAIN_PAGE_KEY -> {
                currentResultPage = MAIN_PAGE_VALUE;
            }
            case PROGRAM_PAGE_KEY -> {
                currentResultPage = PROGRAM_PAGE_VALUE;
            }
            case ORDER_PAGE_KEY -> {
                currentResultPage = ORDER_PAGE_VALUE;
            }
            case HISTORY_PAGE_KEY -> {
                currentResultPage =  HISTORY_PAGE_VALUE;
            }
            default -> {
                currentResultPage = LOGIN_PAGE_VALUE;
            }
        }
        return CommandResult.forward(currentResultPage);
    }
}
