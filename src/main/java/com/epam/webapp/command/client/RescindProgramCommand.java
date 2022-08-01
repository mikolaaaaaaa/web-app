package com.epam.webapp.command.client;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.RequestConstant;
import com.epam.webapp.entity.ProgramState;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.OrderService;
import com.epam.webapp.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RescindProgramCommand implements Command {

    private final ProgramService programService = new ProgramService();
    private final OrderService orderService = new OrderService();

    private static final String PROGRAM_PAGE_VALUE = "WEB-INF/jsp/client/program.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
         long programId = Long.parseLong(req.getParameter(RequestConstant.PROGRAM_ID));
         programService.updateProgramState(programId, ProgramState.RESCINDED);
         return CommandResult.forward(PROGRAM_PAGE_VALUE);
    }

}
