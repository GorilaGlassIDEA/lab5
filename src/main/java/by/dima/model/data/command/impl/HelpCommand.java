package by.dima.model.data.command.impl;

import by.dima.model.data.command.CommandManager;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.command.model.CommandAbstract;
import lombok.Getter;
import lombok.Setter;

public class HelpCommand extends CommandAbstract {
    private final CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        super("help", "Display help for available commands.");
        this.commandManager = commandManager;
    }

    @Override
    public void execute() {
        for (Command command : commandManager.getCommandMap().values()) {
            System.out.println(command.getKey() + ": " + command.getHelp());
        }
    }

}
