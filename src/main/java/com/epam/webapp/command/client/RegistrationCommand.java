package com.epam.webapp.command.client;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.RequestConstant;
import com.epam.webapp.dto.ClientDto;
import com.epam.webapp.entity.ClientType;
import com.epam.webapp.entity.EntityGender;
import com.epam.webapp.entity.UserState;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    private final ClientService service = new ClientService();

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String LOGIN_PAGE = "WEB-INF/jsp/login.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String name = req.getParameter("name");
        LOGGER.info("clienName = {}",name);
        String surname = req.getParameter("surname");
        LOGGER.info("clienSURName = {}",surname);
        String login = req.getParameter("login");
        LOGGER.info("clienlogin = {}",login);
        String password = req.getParameter("password");
        ClientDto clientDto = new ClientDto(
                name,
                surname,
                login,
                password,
                ClientType.USUAL,
                EntityGender.MALE,
                0,
                UserState.ACTIVE
        );
        service.registerClient(clientDto);
        return CommandResult.forward(LOGIN_PAGE);
    }

}
