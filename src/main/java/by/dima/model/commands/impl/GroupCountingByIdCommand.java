package by.dima.model.commands.impl;

import by.dima.model.commands.model.CommandAbstract;

public class GroupCountingByIdCommand extends CommandAbstract {
    public GroupCountingByIdCommand(Long userId) {
        super(userId, "group_counting_by_id");
    }
}
