package com.epam.webapp.command.client;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.RequestConstant;
import com.epam.webapp.constant.SessionConstant;
import com.epam.webapp.entity.Program;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddFeedbackCommand implements Command {

    private static final String PROGRAM_PAGE_VALUE = "WEB-INF/jsp/client/program.jsp";

    private final ProgramService service = new ProgramService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String feedback = req.getParameter(RequestConstant.CLIENT_FEEDBACK);
        HttpSession session = req.getSession();
        Program program = (Program) session.getAttribute(SessionConstant.CLIENT_PROGRAM);
        service.updateClientFeedback(program,feedback);
        return CommandResult.forward(PROGRAM_PAGE_VALUE);
    }
}
