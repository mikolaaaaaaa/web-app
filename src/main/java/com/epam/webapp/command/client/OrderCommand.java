package com.epam.webapp.command.client;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class OrderCommand implements Command {

    private final OrderService service = new OrderService();

    private static final String CLIENT_ORDER_PAGE = "WEB-INF/jsp/client/order.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        return CommandResult.forward(CLIENT_ORDER_PAGE);
    }
}
