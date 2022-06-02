package com.epam.webapp.command.trainer;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.RequestConstant;
import com.epam.webapp.constant.SessionConstant;
import com.epam.webapp.entity.Client;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ShowClientInfoCommand implements Command {

    private final ClientService service = new ClientService();

    private static final String CLIENT_INFO_PAGE = "WEB-INF/jsp/trainer/client_info.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        HttpSession session = req.getSession();
        long clientId = Long.parseLong(req.getParameter(RequestConstant.CLIENT_ID));
        Optional<Client> client = service.getClientById(clientId);
        client.ifPresent(value ->
        {
            session.setAttribute(SessionConstant.CLIENT, value);
            session.setAttribute(SessionConstant.CLIENT_STATE, client.get().
                    getState().
                    toString());
        });
        return CommandResult.forward(CLIENT_INFO_PAGE);
    }

}
