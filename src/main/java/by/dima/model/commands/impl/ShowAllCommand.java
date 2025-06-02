package by.dima.model.commands.impl;

import by.dima.model.commands.model.CommandAbstract;

public class ShowAllCommand extends CommandAbstract {

    public ShowAllCommand(Long userId) {
        super(userId, "show_all");
    }
}
