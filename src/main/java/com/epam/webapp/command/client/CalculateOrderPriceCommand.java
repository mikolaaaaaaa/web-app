package com.epam.webapp.command.client;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.SessionConstant;
import com.epam.webapp.entity.TrainerType;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CalculateOrderPriceCommand implements Command {

    private final OrderService service = new OrderService();

    private static final String TRAINING_COUNT_PARAMETER = "trainingCount";
    private static final String IS_PERSONAL_PARAMETER = "personalTrainer";
    private static final String CLIENT_ORDER_PAGE = "WEB-INF/jsp/client/order.jsp";

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        int trainingCount = Integer.parseInt(req.getParameter(TRAINING_COUNT_PARAMETER));
        boolean isPersonal = Boolean.parseBoolean(req.getParameter(IS_PERSONAL_PARAMETER));
        LOGGER.info(isPersonal);
        double orderPrice = service.calculatePrice(trainingCount,isPersonal);
        session.setAttribute(SessionConstant.ORDER_PRICE, orderPrice);
        if (isPersonal) {
            session.setAttribute(SessionConstant.TRAINER_TYPE, TrainerType.PERSONAL);
        }
        else {
            session.setAttribute(SessionConstant.TRAINER_TYPE,TrainerType.USUAL);
        }
        return CommandResult.forward(CLIENT_ORDER_PAGE);
    }
}
