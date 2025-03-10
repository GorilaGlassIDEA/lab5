package by.dima.model.data.command.impl;

import by.dima.model.data.command.model.Command;
import by.dima.model.service.files.io.ScannerWrapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExitCommand implements Command {
    private String key = "exit";
    private final ScannerWrapper scannerWrapper;

    public ExitCommand(ScannerWrapper scannerWrapper) {
        this.scannerWrapper = scannerWrapper;
    }

    @Override
    public void execute() {
        System.err.println("If you want to save change you can use a save command!\nDo you want to continue exit without save? y/n ");
        String[] answer = scannerWrapper.newLine();
        if (answer.length == 1) {
            if (!answer[0].equals("y")){
                System.err.println("Exit command rejected! Try again or save changes using save command!");
                return;
            }
        } else {
            System.err.println("Exit command rejected because you write invalid answer! Try again or save changes using save command!");
            return;
        }
        scannerWrapper.closeScanner();
    }
}
