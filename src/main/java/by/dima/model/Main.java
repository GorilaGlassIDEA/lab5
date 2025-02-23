package by.dima.model;

import by.dima.model.data.command.CommandManager;

public class Main {
    public static void main(String[] args) {

        CommandManager manager = new CommandManager();
        manager.executeCommand(args);

    }
}