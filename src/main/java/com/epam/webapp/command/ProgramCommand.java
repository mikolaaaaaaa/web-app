package com.epam.webapp.command;

import com.epam.webapp.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProgramCommand implements Command {

     private static final String CLIENT_PROGRAM_PAGE = "WEB-INF/jsp/client/program.jsp";

     @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        return CommandResult.redirect("controller?command=" + "showProgramPage");
    }
}
