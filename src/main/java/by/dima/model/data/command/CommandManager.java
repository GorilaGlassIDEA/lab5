package by.dima.model.data.command;

import by.dima.model.data.CollectionController;
import by.dima.model.data.command.impl.*;
import by.dima.model.data.command.impl.creator.RouteCreator;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.io.ScannerWrapper;
import by.dima.model.service.files.io.add.AddableInfo;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import by.dima.model.service.generate.id.IdGenerateble;
import lombok.Getter;

import java.util.*;

public class CommandManager {
    @Getter
    private final Map<String, Command> commandMap = new HashMap<>();
    private final ScannerWrapper scannerWrapper;

    public CommandManager(CollectionController collectionController, ScannerWrapper scannerWrapper, RouteCreator routeCreator, AddableInfo addableInfo, ParserToJson parserToJson, IdGenerateble idGenerateble) {
        this.scannerWrapper = scannerWrapper;
        Map<Long, Route> routeMap = collectionController.getCollectionForControl();

        Command helpCommand = new HelpCommand();
        Command infoCommand = new InfoCommand(collectionController);
        Command showCommand = new ShowCommand(collectionController);
        Command insertCommand = new InsertCommand(collectionController, parserToJson, idGenerateble, routeCreator);
        Command updateCommand = new UpdateCommand(routeMap, routeCreator, addableInfo.getWriteableFile(), parserToJson);
        Command clearCommand = new ClearCommand(addableInfo.getWriteableFile(), collectionController);
        Command exitCommand = new ExitCommand(scannerWrapper);


        commandMap.put(helpCommand.getKey(), helpCommand);
        commandMap.put(infoCommand.getKey(), infoCommand);
        commandMap.put(showCommand.getKey(), showCommand);
        commandMap.put(insertCommand.getKey(), insertCommand);
        commandMap.put(updateCommand.getKey(), updateCommand);
        commandMap.put(clearCommand.getKey(), clearCommand);
        commandMap.put(exitCommand.getKey(), exitCommand);


    }

    public void executeCommand() {
        String[] arrArgs = scannerWrapper.newLine();
        List<String> args = new ArrayList<>(List.of(arrArgs));
        try {
            String key = args.get(0);
            commandMap.get(key).setArgs(arrArgs);
            commandMap.get(key).execute();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Incorrect command or you dont write any args!");
        }
    }
}
