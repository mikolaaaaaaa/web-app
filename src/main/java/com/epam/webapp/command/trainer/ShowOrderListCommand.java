package com.epam.webapp.command.trainer;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.SessionConstant;
import com.epam.webapp.entity.Order;
import com.epam.webapp.entity.Trainer;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowOrderListCommand implements Command {

    private OrderService service = new OrderService();

    private static final String ORDER_LIST_PAGE = "WEB-INF/jsp/trainer/orders.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        Trainer trainer = (Trainer) session.getAttribute(SessionConstant.USER);
        List<Order> orders = service.findOrdersByTrainerId(trainer.getId());
        session.setAttribute(SessionConstant.ORDER_LIST,orders);
        return CommandResult.forward(ORDER_LIST_PAGE);
    }

}
