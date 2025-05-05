package by.dima.model.commands.impl;

import by.dima.model.commands.model.CommandAbstract;

public class PrintFieldDescendingDistanceCommand extends CommandAbstract {
    public PrintFieldDescendingDistanceCommand(Long userId) {
        super(userId, "print_field_descending_distance");
    }
}
