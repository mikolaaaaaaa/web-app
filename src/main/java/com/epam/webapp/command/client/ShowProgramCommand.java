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
import java.util.Optional;

public class ShowProgramCommand implements Command {

    ProgramService service = new ProgramService();

    private static final String PROGRAM_PAGE_VALUE = "WEB-INF/jsp/client/program.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute(SessionConstant.USER);
        Optional<Program> program = service.showProcessProgram(client.getId());
        program.ifPresent(value -> session.setAttribute(SessionConstant.CLIENT_PROGRAM, value));
        return CommandResult.forward(PROGRAM_PAGE_VALUE);
    }

}
