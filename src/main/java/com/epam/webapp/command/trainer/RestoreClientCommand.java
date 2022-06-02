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

public class RestoreClientCommand implements Command {

    private final TrainerService service = new TrainerService();

    private static final String CLIENT_INFO_PAGE = "WEB-INF/jsp/trainer/client_info.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        long id = Long.parseLong(req.getParameter(RequestConstant.CLIENT_ID));
        service.restoreClient(id, UserState.ACTIVE);
        HttpSession session = req.getSession();
        session.setAttribute(SessionConstant.CLIENT_STATE,UserState.ACTIVE.toString());
        return CommandResult.forward(CLIENT_INFO_PAGE);
    }

}
