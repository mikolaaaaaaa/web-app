package com.epam.webapp.command;

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

public class ShowMainPageCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private final TrainerService service = new TrainerService();

    private static final String CLIENT_MAIN_PAGE = "WEB-INF/jsp/client/main.jsp";
    private static final String LOGIN_PARAMETER = "login";
    private static final String USER_ATTRIBUTE = "user";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        Entity entity = (Entity) session.getAttribute(USER_ATTRIBUTE);
        String name = "";
        if (entity instanceof Client) {
            name = ((Client) entity).getName();
        } else if (entity instanceof Trainer) {
            name = ((Trainer) entity).getName();
        }
        session.setAttribute("name", name);
        return CommandResult.forward(CLIENT_MAIN_PAGE);
    }
}
