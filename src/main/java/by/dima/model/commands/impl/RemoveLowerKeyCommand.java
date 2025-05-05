package by.dima.model.commands.impl;

import by.dima.model.commands.model.CommandAbstract;

public class RemoveLowerKeyCommand extends CommandAbstract {
    public RemoveLowerKeyCommand(Long userId) {
        super(userId, "remove_lower_key");
    }

    @Override
    public void execute() {
        super.execute();

        if (getCommandDTO().getArgCommand() == null) {
            System.out.println("Некорректный ввод, проверьте аргументы!");
        }

    }
}
