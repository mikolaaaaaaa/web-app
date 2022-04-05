package com.epam.webapp.command;

import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CalculateOrderPriceCommand implements Command{

    private final OrderService service = new OrderService();

    private static final String TRAINING_COUNT_ATTRIBUTE = "trainingCount";
    private static final String TRAINER_TYPE_ATTRIBUTE = "trainerType";
    private static final String CLIENT_ORDER_PAGE = "WEB-INF/jsp/client/order.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        int trainingCount = Integer.parseInt((String) req.getParameter(TRAINING_COUNT_ATTRIBUTE));
        String orderCost = service.calculateCoast(trainingCount,true);
        session.setAttribute("orderCoast",orderCost);
        return CommandResult.forward(CLIENT_ORDER_PAGE);
    }
}
