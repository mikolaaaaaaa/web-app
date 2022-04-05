package com.epam.webapp.command;

import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.Program;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowHistoryPageCommand implements Command{

    private final ProgramService service = new ProgramService();
    private static final String HISTORY_PAGE = "WEB-INF/jsp/client/history.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute("user");
        List<Program> finishedPrograms = service.showFinishedClientPrograms(client.getId());
        session.setAttribute("historyPrograms",finishedPrograms);
        return CommandResult.forward(HISTORY_PAGE);
    }
}
