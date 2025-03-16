package by.dima.model.data.command;

import by.dima.model.data.command.model.Command;
import by.dima.model.service.files.io.read.ReadableFile;
import by.dima.model.service.files.io.write.WriteableFile;
import lombok.Getter;
import lombok.Setter;


public class ExecuteScriptCommand implements Command {
    @Setter
    @Getter
    private String key = "execute_script";
    private final CommandManager commandManager;
    private final String myStringContent;

    public ExecuteScriptCommand(CommandManager commandManager, ReadableFile readableFile) {
        this.commandManager = commandManager;
        this.myStringContent = readableFile.getContent();
    }


    @Override
    public void execute() {


    }
}
