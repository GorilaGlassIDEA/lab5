package by.dima.model.commands;

import by.dima.model.commands.impl.InfoCommand;
import by.dima.model.commands.impl.InsertCommand;
import by.dima.model.commands.model.Command;
import by.dima.model.common.CommandDTO;
import by.dima.model.parser.RouteParserToJson;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Getter
public class CommandManager {
    private final Map<String, Command> commandMap = new HashMap<>();


    public CommandManager(RouteParserToJson parser, Long userId, Logger logger) {
        Command insertCommand = new InsertCommand(parser, userId, logger);
        Command infoCommand = new InfoCommand();

        commandMap.put(insertCommand.getKey(), insertCommand);
        commandMap.put(infoCommand.getKey(), infoCommand);

    }

    public CommandDTO execute(Command command) {
        command.execute();
        return command.getCommandDTO();
    }

}
