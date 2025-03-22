package by.dima.model.data.command.impl;

import by.dima.model.data.command.model.CommandAbstract;

public class RemoveLowerKeyCommand extends CommandAbstract {

    public RemoveLowerKeyCommand() {
        super("remove_lower_key", "Remove all elements from the collection whose key is less than the specified one.");
    }

    @Override
    public void execute() {

    }
}
