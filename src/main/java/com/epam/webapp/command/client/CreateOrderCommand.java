package com.epam.webapp.command.client;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.SessionConstant;
import com.epam.webapp.dto.OrderDto;
import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.OrderState;
import com.epam.webapp.entity.TrainerType;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.ClientService;
import com.epam.webapp.service.OrderService;
import com.epam.webapp.service.TrainerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class CreateOrderCommand implements Command {

    private final ClientService clientService = new ClientService();
    private final OrderService orderService = new OrderService();
    private final TrainerService trainerService = new TrainerService();

    private static final String CLIENT_ORDER_PAGE = "WEB-INF/jsp/client/order.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute(SessionConstant.USER);
        TrainerType trainerType = (TrainerType) session.getAttribute(SessionConstant.TRAINER_TYPE);
        Long trainerId = trainerService.findTrainerForClient();
        double price = (double) session.getAttribute(SessionConstant.ORDER_PRICE);

        if (Objects.equals(trainerId, TrainerService.TRAINER_NOT_FOUND)) {
            session.setAttribute(SessionConstant.TRAINER_NOT_FOUND, true);
        } else if (clientService.hasOrder(client.getId())) {
            session.setAttribute(SessionConstant.HAS_ORDER, true);
        } else if (orderService.isPayable(client, price)) {
            session.setAttribute(SessionConstant.NOT_ENOUGH_MONEY, "false");
            orderService.createOrder(new OrderDto(
                            client.getId(),
                            trainerId,
                            Date.valueOf(LocalDate.now()),
                            trainerType,
                            price,
                            true,
                            OrderState.CREATED),
                            client
            );
        } else {
            session.setAttribute(SessionConstant.NOT_ENOUGH_MONEY, "true");
        }

        return CommandResult.forward(CLIENT_ORDER_PAGE);
    }
}
