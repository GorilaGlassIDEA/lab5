package by.dima.model.data.command.model;

import by.dima.model.data.CollectionController;
import by.dima.model.data.command.model.impl.*;
import by.dima.model.data.route.model.main.CreateRouteUsingScanner;
import by.dima.model.data.command.model.model.Command;
import by.dima.model.service.files.io.ScannerWrapper;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import by.dima.model.service.generate.id.IdGenerateble;
import by.dima.model.service.util.GetSecondArgFromArgsUtil;
import lombok.Getter;

import java.util.*;

/**
 * Данный класс управляет всеми командами через реализацию паттерна команда
 *
 * @see Command
 */
public class CommandManager {
    @Getter
    private final Map<String, Command> commandMap = new HashMap<>();
    private final ScannerWrapper scannerWrapper;
    private final LinkedList<String> historyCommandQueue;

    public CommandManager(CollectionController collectionController,
                          ScannerWrapper scannerWrapper,
                          CreateRouteUsingScanner routeCreator,
                          ParserToJson parserToJson,
                          IdGenerateble idGenerateble) {
        this.scannerWrapper = scannerWrapper;
        this.historyCommandQueue = new LinkedList<>();

        Command helpCommand = new HelpCommand(this);
        Command infoCommand = new InfoCommand(collectionController);
        Command showCommand = new ShowCommand(collectionController);
        Command insertCommand = new InsertCommand(collectionController, parserToJson, idGenerateble, routeCreator);
        Command updateCommand = new UpdateCommand(routeCreator, collectionController);
        Command clearCommand = new ClearCommand(collectionController);
        Command saveCommand = new SaveCommand(collectionController);
        Command exitCommand = new ExitCommand(scannerWrapper);
        Command removeKeyCommand = new RemoveKeyCommand(collectionController);
        Command historyCommand = new HistoryCommand(historyCommandQueue);
        Command executeScriptCommand = new ExecuteScriptCommand(this);
        Command replaceIfLoweCommand = new ReplaceIfLoweCommand(idGenerateble, collectionController, routeCreator);
        Command removeLowerKeyCommand = new RemoveLowerKeyCommand(collectionController);
        Command groupCountingByIdCommand = new GroupCountingByIdCommand(collectionController);
        Command printAscendingCommand = new PrintAscendingCommand(collectionController);
        Command printFieldDescendingDistanceCommand = new PrintFieldDescendingDistanceCommand(collectionController);
        Command addCommand = new AddCommand(collectionController, idGenerateble);

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
        commandMap.put(executeScriptCommand.getKey(), executeScriptCommand);
        commandMap.put(replaceIfLoweCommand.getKey(), replaceIfLoweCommand);
        commandMap.put(removeLowerKeyCommand.getKey(), removeLowerKeyCommand);
        commandMap.put(groupCountingByIdCommand.getKey(), groupCountingByIdCommand);
        commandMap.put(printAscendingCommand.getKey(), printAscendingCommand);
        commandMap.put(printFieldDescendingDistanceCommand.getKey(), printFieldDescendingDistanceCommand);
        commandMap.put(addCommand.getKey(), addCommand);

    }

    /**
     * Данный метод позволяет запустить команду из структуры Map, в которой хранятся пара
     * {@link Long} и {@link Command}. Ключ получается с ввода в консоли через класс {@link ScannerWrapper}
     */
    public void executeCommand() {
        String[] arrArgs = scannerWrapper.newLine();
        List<String> args = new ArrayList<>(List.of(arrArgs));
        try {
            String key = args.get(0);
            Command thisCommand = commandMap.get(key);
            thisCommand.setArgs(GetSecondArgFromArgsUtil.getSecondArg(arrArgs));
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

    /**
     * Данный метод позволяет запускать командны, переданные в аргументы метода. Данный метод необходим
     * для реализации команды запуска команд из файла
     *
     * @param commands
     * @see ExecuteScriptCommand
     */

    public void executeCommand(String... commands) {
        String thisCommandString = "";
        int count = commands.length;
        while (count > 0) {
            try {
                for (String keyCommand : commands) {
                    count -= 1;
                    commandMap.get(keyCommand).execute();
                    System.out.println("Command: " + keyCommand + " completed!");
                    System.out.println("---------------------------------------");

                }
            } catch (NoSuchElementException e) {
                System.err.println("Command " + thisCommandString + " is not found");
            }
        }
    }
}
