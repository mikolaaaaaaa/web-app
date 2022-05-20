package com.epam.webapp.command.client;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.SessionConstant;
import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.Program;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowHistoryCommand implements Command {

    private final ProgramService service = new ProgramService();
    private static final String HISTORY_PAGE = "WEB-INF/jsp/client/history.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute(SessionConstant.USER);
        List<Program> finishedPrograms = service.showFinishedClientPrograms(client.getId());
        if (finishedPrograms.isEmpty()) {
            session.setAttribute(SessionConstant.EMPTY_HISTORY, true);
        }
        else {
            session.setAttribute(SessionConstant.EMPTY_HISTORY,false);
            session.setAttribute(SessionConstant.HISTORY_PROGRAMS, finishedPrograms);
        }
        return CommandResult.forward(HISTORY_PAGE);
    }
}
