package by.dima.model.commands.impl;

import by.dima.model.commands.model.CommandAbstract;

public class RemoveKeyCommand extends CommandAbstract {
    public RemoveKeyCommand(Long userId) {
        super(userId, "remove_key");
    }
}
