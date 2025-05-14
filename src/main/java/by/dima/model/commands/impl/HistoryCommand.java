package by.dima.model.commands.impl;

import by.dima.model.commands.model.CommandAbstract;

public class HistoryCommand extends CommandAbstract {
    public HistoryCommand(Long userId) {
        super(userId, "history");
    }
}
