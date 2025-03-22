package by.dima.model.data.command.model.impl;

import by.dima.model.data.command.model.CommandManager;
import by.dima.model.data.command.model.model.CommandAbstract;
import by.dima.model.service.files.io.read.ReadFileFiles;
import by.dima.model.service.files.io.read.ReadableFile;
import by.dima.model.service.iterator.TextIterable;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ExecuteScriptCommand extends CommandAbstract {
    private final CommandManager commandManager;
    private String content;
    private TextIterable textIterable;


    public ExecuteScriptCommand(CommandManager commandManager) {
        super("execute_script", "Execute commands from a specified file.");
        this.commandManager = commandManager;
    }

    @Override
    public void setArgs(String arg) {
        ReadableFile readableFile = new ReadFileFiles(arg);
        content = readableFile.getContent();
        textIterable = new TextIterable(content);
    }

    @Override
    public void execute() {
        for (String command : textIterable) {
            String actualCommand = command.trim();
            commandManager.executeCommand(actualCommand);
        }
    }
}
