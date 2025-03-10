package by.dima.model.data.command.impl;

import by.dima.model.data.command.model.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryCommand implements Command {

    private String key = "history";

    @Override
    public void execute() {
        //TODO: история последних 8 команд (без аргументов)
    }
}
