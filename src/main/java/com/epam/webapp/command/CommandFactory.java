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
                return new HistoryCommand();
            }
            case "showMainPage" -> {
                return new ShowMainPage();
            }
            case "showProgramPage" -> {
                return new ShowProgramPage();
            }
            default -> {
                 throw new IllegalArgumentException("Unknown command = " + command);
            }
        }
    }
}
