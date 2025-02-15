package by.dima.model;

import by.dima.model.data.command.CommandManager;

public class Main {
    public static void main(String[] args) {

        CommandManager commandManager = new CommandManager();
        commandManager.executeCommand(args[0]);


    }
}