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
        scannerWrapper.closeScanner();
    }
}
