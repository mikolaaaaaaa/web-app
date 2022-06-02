package com.epam.webapp.command.trainer;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.RequestConstant;
import com.epam.webapp.constant.SessionConstant;
import com.epam.webapp.entity.UserState;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.TrainerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteClientCommand implements Command {

    private final TrainerService service = new TrainerService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        long id = Long.parseLong(req.getParameter(RequestConstant.CLIENT_ID));
        service.deleteClient(id, UserState.DELETED);
        HttpSession session = req.getSession();
        session.setAttribute(SessionConstant.CLIENT_STATE,UserState.DELETED.toString());
        return CommandResult.redirect("controller?command=" + "showClients");
    }

}
