package com.epam.webapp.command;

import com.epam.webapp.command.client.*;
import com.epam.webapp.command.common.GetRegistrationFormCommand;
import com.epam.webapp.command.common.LanguageChangeCommand;
import com.epam.webapp.command.common.LoginCommand;
import com.epam.webapp.command.common.LogoutCommand;
import com.epam.webapp.command.trainer.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LOGIN = "login";
    private static final String LANGUAGE_CHANGE = "languageChange";
    private static final String ORDER = "order";
    private static final String PROGRAM = "program";
    private static final String HISTORY = "history";
    private static final String SHOW_MAIN_PAGE = "showMainPage";
    private static final String SHOW_PROGRAM_PAGE = "showProgramPage";
    private static final String LOGOUT = "logout";
    private static final String SHOW_HISTORY_PROGRAM = "showHistoryProgram";
    private static final String CALCULATE_ORDER_PRICE = "calculateOrderPrice";
    private static final String ORDER_PAY = "orderPay";
    private static final String ADD_FEEDBACK = "addFeedback";
    private static final String SHOW_CASH_IN = "showCashIn";
    private static final String CASH_IN = "cashIn";
    private static final String CREATE_ORDER = "createOrder";
    private static final String SHOW_CLIENTS = "showClients";
    private static final String UNKNOWN_COMMAND = "Unknown command = {}";
    private static final String SHOW_CLIENT_INFO = "showClientInfo";
    private static final String SHOW_ORDERS = "showOrders";
    private static final String BLOCK_CLIENT = "blockClient";
    private static final String UNBLOCK_CLIENT = "unblockClient";
    private static final String DELETE_CLIENT = "deleteClient";
    private static final String RESCIND_PROGRAM = "rescindProgram";
    private static final String GET_CLIENT_REGISTRATION_FORM = "getClientRegistrationForm";
    private static final String REGISTER_CLIENT = "registerClient";

    public Command create(String command) {
        LOGGER.info("command - {}", command);
        switch (command) {
            case LOGIN -> {
                return new LoginCommand();
            }
            case LANGUAGE_CHANGE -> {
                return new LanguageChangeCommand();
            }
            case ORDER -> {
                return new OrderCommand();
            }
            case PROGRAM -> {
                return new ProgramCommand();
            }
            case HISTORY -> {
                return new ShowHistoryCommand();
            }
            case SHOW_MAIN_PAGE -> {
                return new ShowMainCommand();
            }
            case SHOW_PROGRAM_PAGE -> {
                return new ShowProgramCommand();
            }
            case LOGOUT -> {
                return new LogoutCommand();
            }
            case SHOW_HISTORY_PROGRAM -> {
                return new ShowFinishedProgramCommand();
            }
            case CALCULATE_ORDER_PRICE -> {
                return new CalculateOrderPriceCommand();
            }
            case ORDER_PAY -> {
                return new PayOrderCommand();
            }
            case ADD_FEEDBACK -> {
                return new AddFeedbackCommand();
            }
            case SHOW_CASH_IN -> {
                return new ShowCashInCommand();
            }
            case CASH_IN -> {
                return new CashInCommand();
            }
            case CREATE_ORDER -> {
                return new CreateOrderCommand();
            }
            case SHOW_CLIENTS -> {
                return new ShowClientListCommand();
            }
            case SHOW_CLIENT_INFO -> {
                return new ShowClientInfoCommand();
            }
            case SHOW_ORDERS -> {
                return new ShowOrderListCommand();
            }
            case BLOCK_CLIENT -> {
                return new BlockClientCommand();
            }
            case UNBLOCK_CLIENT -> {
                return new UnblockClientCommand();
            }
            case DELETE_CLIENT -> {
                return new DeleteClientCommand();
            }
            case RESCIND_PROGRAM -> {
                return new RescindProgramCommand();
            }
            case GET_CLIENT_REGISTRATION_FORM -> {
                return new GetRegistrationFormCommand();
            }
            case REGISTER_CLIENT -> {
                return new RegistrationCommand();
            }
            default -> {
                LOGGER.info(UNKNOWN_COMMAND,command);
                 throw new IllegalArgumentException(UNKNOWN_COMMAND + command);
            }
        }
    }
}
