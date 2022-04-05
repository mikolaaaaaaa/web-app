package com.epam.webapp.controller;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandFactory;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private final CommandFactory commandFactory = new CommandFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandLine = req.getParameter("command");
        Command command = commandFactory.create(commandLine);
        try {
            CommandResult commandResult = command.execute(req,resp);
            processCommandResult(commandResult,req,resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    private void processCommandResult(CommandResult commandResult, HttpServletRequest req, HttpServletResponse resp) {
       if (commandResult != null) {
           if (commandResult.isRedirect()) {
               try {
                   resp.sendRedirect(commandResult.getPage());
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           else {
               RequestDispatcher requestDispatcher = req.getRequestDispatcher(commandResult.getPage());
               try {
                   requestDispatcher.forward(req,resp);
               } catch (ServletException | IOException e) {
                   e.printStackTrace();
               }
           }
       }

    }


}
