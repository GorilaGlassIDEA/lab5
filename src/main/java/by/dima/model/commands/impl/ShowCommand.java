package by.dima.model.commands.impl;


import by.dima.model.commands.model.CommandAbstract;

public class ShowCommand extends CommandAbstract {

    public ShowCommand(Long userId) {
        super(userId, "show");
    }
}
