package by.dima.model.auth.mode;

import by.dima.model.auth.AuthService;
import by.dima.model.auth.read.data.CLIReadData;
import by.dima.model.auth.read.data.ReadableData;

import java.util.Scanner;

public class ScannerValidateUserFacade extends AbstractValidateUserFacade {

    public ScannerValidateUserFacade(AuthService authService) {
        super(new Scanner(System.in), authService);
    }

    @Override
    public ReadableData getReadableData() {
        return new CLIReadData();
    }
}
