package com.epam.webapp.command.client;

import com.epam.webapp.command.Command;
import com.epam.webapp.command.CommandResult;
import com.epam.webapp.constant.RequestConstant;
import com.epam.webapp.constant.SessionConstant;
import com.epam.webapp.entity.Client;
import com.epam.webapp.exception.ServiceException;
import com.epam.webapp.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CashInCommand implements Command {

    private static final String CLIENT_CASH_IN_PAGE = "WEB-INF/jsp/client/CashIn.jsp";

    private final ClientService service = new ClientService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        double amountOfMoney = Double.parseDouble(req.getParameter(RequestConstant.AMOUNT_OF_MONEY));
        Client sessionClient = (Client) session.getAttribute(SessionConstant.USER);
        Optional<Client> databaseClient = service.getClientById(sessionClient.getId());
        if (databaseClient.isPresent()) {
            Client client = databaseClient.get();
            double currentBalance = client.getBalance();
            double resultBalance = currentBalance + amountOfMoney;
            service.updateBalance(sessionClient,resultBalance);
        }
        return CommandResult.forward(CLIENT_CASH_IN_PAGE);
    }

}
