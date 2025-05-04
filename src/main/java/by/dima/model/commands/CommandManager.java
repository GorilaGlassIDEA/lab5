package by.dima.model.commands;

import by.dima.model.commands.impl.ClearCommand;
import by.dima.model.commands.impl.InfoCommand;
import by.dima.model.commands.impl.InsertCommand;
import by.dima.model.commands.impl.ShowCommand;
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
    private final Long userId;

    public CommandManager(RouteParserToJson parser, Long userId, Logger logger) {
        this.userId = userId;
        Command insertCommand = new InsertCommand(parser, userId, logger);
        Command infoCommand = new InfoCommand(userId);
        Command clearCommand = new ClearCommand(userId);
        Command showCommand = new ShowCommand(userId);

        commandMap.put(insertCommand.getKey(), insertCommand);
        commandMap.put(infoCommand.getKey(), infoCommand);
        commandMap.put(clearCommand.getKey(), clearCommand);
        commandMap.put(showCommand.getKey(), showCommand);

    }

    public CommandDTO execute(Command command) {
        command.execute();
        return command.getCommandDTO();
    }

}
