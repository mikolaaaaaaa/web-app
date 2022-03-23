package com.epam.webapp.command;

import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.Trainer;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.ClientService;
import com.epam.webapp.service.TrainerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private final TrainerService trainerService = new TrainerService();
    private final ClientService clientService = new ClientService();

    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String LOGIN_PAGE = "WEB-INF/jsp/login.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String login = req.getParameter(LOGIN_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);

        Optional<Client> client = clientService.login(login, password);
        Optional<Trainer> trainer = trainerService.login(login, password);

        if (client.isPresent()) {
            req.getSession().setAttribute("user", client.get());
            return CommandResult.redirect("controller?command=" + "showMainPage");
        } else if (trainer.isPresent()) {
            req.getSession().setAttribute("user", trainer.get());
            return CommandResult.redirect("controller?command=" + "showMainPage");
        } else {
            req.getSession().setAttribute("errorMessage", "invalid credentials");
            return CommandResult.forward(LOGIN_PAGE);
        }

    }


}
