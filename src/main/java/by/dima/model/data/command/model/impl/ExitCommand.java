package by.dima.model.data.command.model.impl;

import by.dima.model.data.command.model.model.CommandAbstract;
import by.dima.model.service.files.io.ScannerWrapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExitCommand extends CommandAbstract {
    private final ScannerWrapper scannerWrapper;

    public ExitCommand(ScannerWrapper scannerWrapper) {
        super("exit", "Exit the program without or you can do saving to use save command.");
        this.scannerWrapper = scannerWrapper;
    }

    @Override
    public void execute() {
        System.err.println("If you want to save change you can use a save command!\nDo you want to continue exit without save? y/n ");
        String[] answer = scannerWrapper.newLine();
        if (answer.length == 1) {
            if (!answer[0].equals("y")) {
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
