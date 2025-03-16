package by.dima.model.data.command;

import by.dima.model.data.command.model.Command;
import by.dima.model.service.files.io.read.ReadFileBufferReader;
import by.dima.model.service.files.io.read.ReadableFile;
import by.dima.model.service.files.io.write.WriteableFile;
import by.dima.model.service.iterator.TextIterable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExecuteScriptCommand implements Command {
    @Setter
    @Getter
    private String key = "execute_script";
    private final CommandManager commandManager;
    private ReadableFile readableFile;
    private String content;
    private TextIterable textIterable;


    public ExecuteScriptCommand(CommandManager commandManager, ReadableFile readableFile) {
        this.commandManager = commandManager;
        this.readableFile = readableFile;
    }

    @Override
    public void setArgs(String... args) {
        List<String> argsList = new ArrayList<>(List.of(args));
        if (!argsList.isEmpty() && argsList.size() > 1) {
            String pathForOpening = args[1];
            readableFile = new ReadFileBufferReader(pathForOpening);
            content = readableFile.getContent();
        }
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
