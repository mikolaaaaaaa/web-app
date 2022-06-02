package com.epam.webapp.command.client;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.SessionConstant;
import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.Entity;
import com.epam.webapp.entity.Trainer;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.TrainerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowMainCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private final TrainerService service = new TrainerService();

    private static final String CLIENT_MAIN_PAGE = "WEB-INF/jsp/client/main.jsp";
    private static final String TRAINER_MAIN_PAGE = "WEB-INF/jsp/trainer/main.jsp";
    private static final String LOGIN_PARAMETER = "login";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        Entity entity = (Entity) session.getAttribute(SessionConstant.USER);
        String name = "";
        String currentPage = "";
        if (entity instanceof Client client) {
            name = client.getName();
            currentPage = CLIENT_MAIN_PAGE;
        } else if (entity instanceof Trainer trainer) {
            name = trainer.getName();
            currentPage = TRAINER_MAIN_PAGE;
        }
        session.setAttribute(SessionConstant.NAME, name);
        return CommandResult.forward(currentPage);
    }
}
