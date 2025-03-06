package by.dima.model.data.command;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.command.impl.*;
import by.dima.model.data.command.impl.creator.RouteCreator;
import by.dima.model.data.command.model.Command;
import by.dima.model.service.files.io.add.AddableInfo;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import by.dima.model.service.generate.id.IdGenerateble;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    @Getter
    private final Map<String, Command> commandMap = new HashMap<>();
    private String[] args;

    public CommandManager(Models models, String[] args, RouteCreator routeCreator, AddableInfo addableInfo, ParserToJson parserToJson, IdGenerateble idGenerateble) {
        Command helpCommand = new HelpCommand();
        Command infoCommand = new InfoCommand(models);
        Command insertCommand = new InsertCommand(addableInfo, parserToJson, idGenerateble, routeCreator);
        Command updateCommand = new UpdateCommand(models.getRoutesMap(), args, routeCreator);
        Command clearCommand = new ClearCommand(addableInfo.getWriteableFile(), models);

        commandMap.put(helpCommand.getKey(), helpCommand);
        commandMap.put(infoCommand.getKey(), infoCommand);
        commandMap.put(insertCommand.getKey(), insertCommand);
        commandMap.put(updateCommand.getKey(), updateCommand);
        commandMap.put(clearCommand.getKey(), clearCommand);

        this.args = args;
    }

    public void executeCommand() {
        try {
            commandMap.get(args[0]).execute();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Incorrect command or you dont write any args!");
        }
    }

}
