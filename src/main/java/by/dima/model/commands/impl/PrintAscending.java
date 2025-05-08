package by.dima.model.commands.impl;

import by.dima.model.commands.model.CommandAbstract;

public class PrintAscending extends CommandAbstract {

    public PrintAscending(Long userId) {
        super(userId, "print_ascending");
    }
}
