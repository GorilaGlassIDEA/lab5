package by.dima.model.data.command.impl;

import by.dima.model.data.command.model.Command;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class HistoryCommand implements Command {

    private String key = "history";
    private List<String> historyCommandList;

    public HistoryCommand(LinkedList<String> historyCommandList) {
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
