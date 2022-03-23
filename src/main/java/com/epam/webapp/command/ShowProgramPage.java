package com.epam.webapp.command;

import com.epam.webapp.entity.Client;
import com.epam.webapp.entity.Program;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ShowProgramPage implements Command {

    ProgramService service = new ProgramService();

    private static final String PROGRAM_PAGE_VALUE = "WEB-INF/jsp/client/program.jsp";
    private static final String CLIENT_ATTRIBUTE_KEY = "user";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute(CLIENT_ATTRIBUTE_KEY);
        Optional<Program> program = service.showClientProgram(client.getId());
        program.ifPresent(value -> session.setAttribute("program", value));
        return CommandResult.forward(PROGRAM_PAGE_VALUE);
    }

}
