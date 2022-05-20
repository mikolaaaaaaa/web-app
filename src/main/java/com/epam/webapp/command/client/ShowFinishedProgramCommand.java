package com.epam.webapp.command.client;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.SessionConstant;
import com.epam.webapp.entity.Program;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.ProgramService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ShowFinishedProgramCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private final ProgramService service = new ProgramService();

    private static final String PROGRAM_PAGE = "WEB-INF/jsp/client/program.jsp";
    private static final String PROGRAM_ID_PARAMETER_NAME = "programId";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        long programId = Integer.parseInt(req.getParameter(PROGRAM_ID_PARAMETER_NAME));
        Optional<Program> program = service.showFinishedProgram(programId);
        session.setAttribute(SessionConstant.CLIENT_PROGRAM,program.get());
        return CommandResult.forward(PROGRAM_PAGE);
    }
}
