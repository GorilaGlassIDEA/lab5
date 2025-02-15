package by.dima.model.data.command.impl;

import by.dima.model.data.command.model.Command;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HelpCommand implements Command {
    private String key;

    public HelpCommand() {
        key = "help";
    }

    @Override
    public void execute() {
        System.out.println("Справка по доступным командам: ...");
        //TODO: дописать справку
    }

}
