package by.dima.model.data.command.impl;

import by.dima.model.data.command.CommandManager;
import by.dima.model.data.command.model.Command;
import by.dima.model.service.files.io.read.ReadFileBufferReader;
import by.dima.model.service.files.io.read.ReadFileFiles;
import by.dima.model.service.files.io.read.ReadableFile;
import by.dima.model.service.iterator.TextIterable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class ExecuteScriptCommand implements Command {
    @Setter
    @Getter
    private String key = "execute_script";
    private final CommandManager commandManager;
    private String content;
    private TextIterable textIterable;


    public ExecuteScriptCommand(CommandManager commandManager) {
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
