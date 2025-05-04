package by.dima.model.commands.impl;

import by.dima.model.commands.model.CommandAbstract;

public class HelpCommand extends CommandAbstract {

    public HelpCommand(Long userId) {
        super(userId, "help");
    }
}
