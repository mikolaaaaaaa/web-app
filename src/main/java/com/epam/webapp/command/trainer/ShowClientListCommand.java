package com.epam.webapp.command.trainer;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.SessionConstant;
import com.epam.webapp.entity.Client;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.TrainerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowClientListCommand implements Command {

    private static final String CLIENT_LIST_PAGE = "WEB-INF/jsp/trainer/clients.jsp";

    private final TrainerService service = new TrainerService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        List<Client> clients = service.showClientList();
        HttpSession session = req.getSession();
        session.setAttribute(SessionConstant.CLIENT_LIST, clients);
        return CommandResult.forward(CLIENT_LIST_PAGE);
    }

}
