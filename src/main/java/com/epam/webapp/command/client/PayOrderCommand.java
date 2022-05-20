package com.epam.webapp.command.client;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.command.client.CreateOrderCommand;
import com.epam.webapp.constant.SessionConstant;
import com.epam.webapp.entity.Client;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.ClientService;
import com.epam.webapp.service.OrderService;
import com.epam.webapp.service.TrainerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PayOrderCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private final OrderService orderService = new OrderService();
    private final ClientService clientService = new ClientService();
    private final TrainerService trainerService = new TrainerService();

    private static final String CLIENT_ORDER_PAGE = "WEB-INF/jsp/client/order.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute(SessionConstant.USER);
        double orderPrice = (double) session.getAttribute(SessionConstant.ORDER_PRICE);

        if (clientService.hasOrder(client.getId())) {
            session.setAttribute(SessionConstant.HAS_ORDER, true);
        } else if (orderService.isPayable(client, orderPrice)) {
            session.setAttribute(SessionConstant.NOT_ENOUGH_MONEY, "false");
            orderService.pay(client, orderPrice);
            CreateOrderCommand createOrderCommand = new CreateOrderCommand();
            return createOrderCommand.execute(req, resp);
        } else {
            session.setAttribute(SessionConstant.NOT_ENOUGH_MONEY, "true");
        }
        return CommandResult.forward(CLIENT_ORDER_PAGE);
    }
}
