package com.epam.webapp.command;

public class CommandResult {
    private final boolean isRedirect;
    private final String page;

    private CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page,false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page,true);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}
