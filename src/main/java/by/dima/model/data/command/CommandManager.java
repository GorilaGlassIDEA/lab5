package by.dima.model.data.command;

import by.dima.model.data.CollectionController;
import by.dima.model.data.command.impl.*;
import by.dima.model.data.command.impl.creator.RouteCreator;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.io.ScannerWrapper;
import by.dima.model.service.files.io.write.WriteableFile;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import by.dima.model.service.generate.id.IdGenerateble;
import lombok.Getter;

import java.util.*;

public class CommandManager {
    @Getter
    private final Map<String, Command> commandMap = new HashMap<>();
    private final ScannerWrapper scannerWrapper;
    private final LinkedList<String> historyCommandQueue;

    public CommandManager(CollectionController collectionController, ScannerWrapper scannerWrapper, RouteCreator routeCreator, WriteableFile writeableFile, ParserToJson parserToJson, IdGenerateble idGenerateble) {
        this.scannerWrapper = scannerWrapper;
        this.historyCommandQueue = new LinkedList<>();
        Map<Long, Route> routeMap = collectionController.getCollectionForControl();

        Command helpCommand = new HelpCommand();
        Command infoCommand = new InfoCommand(collectionController);
        Command showCommand = new ShowCommand(collectionController);
        Command insertCommand = new InsertCommand(collectionController, parserToJson, idGenerateble, routeCreator);
        Command updateCommand = new UpdateCommand(routeMap, routeCreator, writeableFile, parserToJson);
        Command clearCommand = new ClearCommand(collectionController);
        Command saveCommand = new SaveCommand(collectionController);
        Command exitCommand = new ExitCommand(scannerWrapper);
        Command removeKeyCommand = new RemoveKeyCommand(collectionController);
        Command historyCommand = new HistoryCommand(historyCommandQueue);

        commandMap.put(helpCommand.getKey(), helpCommand);
        commandMap.put(infoCommand.getKey(), infoCommand);
        commandMap.put(showCommand.getKey(), showCommand);
        commandMap.put(insertCommand.getKey(), insertCommand);
        commandMap.put(updateCommand.getKey(), updateCommand);
        commandMap.put(clearCommand.getKey(), clearCommand);
        commandMap.put(removeKeyCommand.getKey(), removeKeyCommand);
        commandMap.put(exitCommand.getKey(), exitCommand);
        commandMap.put(saveCommand.getKey(), saveCommand);
        commandMap.put(historyCommand.getKey(), historyCommand);


    }

    public void executeCommand() {
        String[] arrArgs = scannerWrapper.newLine();
        List<String> args = new ArrayList<>(List.of(arrArgs));
        try {
            String key = args.get(0);
            Command thisCommand = commandMap.get(key);
            thisCommand.setArgs(arrArgs);
            thisCommand.execute();
            if (!(thisCommand instanceof HistoryCommand))
                historyCommandQueue.addLast(key);
            if (historyCommandQueue.size() > 8) {
                historyCommandQueue.removeFirst();
            }


        } catch (NullPointerException e) {
            System.err.println("Incorrect command or you dont write any args!" + " NULL POINTER");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Incorrect command or you dont write any args!" + " ARRAY BOUNDS");
        }
    }
}
