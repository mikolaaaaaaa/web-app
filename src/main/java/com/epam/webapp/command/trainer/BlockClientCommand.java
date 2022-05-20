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

public class BlockClientCommand implements Command {

    private final TrainerService service = new TrainerService();

    private static final String CLIENT_INFO_PAGE = "WEB-INF/jsp/trainer/client_info.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        long clientId = Long.parseLong(req.getParameter(RequestConstant.CLIENT_ID));
        service.blockClient(clientId, UserState.BLOCKED);
        HttpSession session = req.getSession();
        session.setAttribute(SessionConstant.CLIENT_STATE, UserState.BLOCKED.toString());
        return CommandResult.forward(CLIENT_INFO_PAGE);
    }

}
