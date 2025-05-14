package by.dima.model.commands.impl;


import by.dima.model.commands.model.CommandAbstract;

public class ClearCommand extends CommandAbstract {


    public ClearCommand(Long userId) {
        super(userId, "clear");
    }

}
