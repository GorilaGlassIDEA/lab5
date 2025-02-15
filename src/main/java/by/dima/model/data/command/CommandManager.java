package by.dima.model.data.command;

import by.dima.model.data.command.impl.HelpCommand;
import by.dima.model.data.command.model.Command;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    @Getter
    Map<String, Command> commandMap = new HashMap<>();
    Command helpCommand = new HelpCommand();

    public CommandManager() {
        commandMap.put(helpCommand.getKey(), helpCommand);
    }

    public void executeCommand(String key){
        commandMap.get(key).execute();
    }

}
