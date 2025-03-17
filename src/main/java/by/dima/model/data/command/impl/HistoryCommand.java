package by.dima.model.data.command.impl;

import by.dima.model.data.command.model.Command;
import by.dima.model.data.command.model.CommandAbstract;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class HistoryCommand extends CommandAbstract {

    private List<String> historyCommandList;

    public HistoryCommand(LinkedList<String> historyCommandList) {
        super("history", "Display the last 8 commands.");
        this.historyCommandList = historyCommandList;

    }

    @Override
    public void execute() {
        if (historyCommandList.isEmpty()) {
            System.err.println("Your list is empty!");
        } else {
            System.out.print("||");
            for (int i = 0; i < historyCommandList.size(); i++) {
                System.out.print(" " + (i + 1) + " " + historyCommandList.get(i) + " ||");
            }
            System.out.println();
        }
    }
}
