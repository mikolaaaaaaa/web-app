package com.epam.webapp.command;

import com.epam.webapp.entity.Program;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.ProgramService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Optional;

public class ShowFinishedProgramPageCommand implements Command{

    private static final Logger LOGGER = LogManager.getLogger();

    private final ProgramService service = new ProgramService();

    private static final String PROGRAM_PAGE_VALUE = "WEB-INF/jsp/client/program.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        long programId = Integer.parseInt((String) req.getParameter("programId"));
        LOGGER.info("programId = {}",programId);
        Optional<Program> program = service.showFinishedProgram(programId);
        session.setAttribute("program",program.get());
        return CommandResult.forward(PROGRAM_PAGE_VALUE);
    }
}
