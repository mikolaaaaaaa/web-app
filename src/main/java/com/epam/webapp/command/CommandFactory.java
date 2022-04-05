package com.epam.webapp.command;

public class CommandFactory {

    public Command create(String command) {
        switch (command) {
            case "login" -> {
                return new LoginCommand();
            }
            case "languageChange" -> {
                return new LanguageChangeCommand();
            }
            case "order" -> {
                return new OrderCommand();
            }
            case "program" -> {
                return new ProgramCommand();
            }
            case "history" -> {
                return new ShowHistoryPageCommand();
            }
            case "showMainPage" -> {
                return new ShowMainPageCommand();
            }
            case "showProgramPage" -> {
                return new ShowProgramPageCommand();
            }
            case "logout" -> {
                return new LogoutCommand();
            }
            case "showHistoryProgram" -> {
                return new ShowFinishedProgramPageCommand();
            }
            case "calculateOrderPrice" -> {
                return new CalculateOrderPriceCommand();
            }
            default -> {
                 throw new IllegalArgumentException("Unknown command = " + command);
            }
        }
    }
}
