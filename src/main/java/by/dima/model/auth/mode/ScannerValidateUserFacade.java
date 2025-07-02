package by.dima.model.auth.mode;

import by.dima.model.auth.AuthService;
import by.dima.model.auth.read.impl.CLIReadData;
import by.dima.model.auth.read.ReadableData;

import java.util.Iterator;
import java.util.Scanner;

public class ScannerValidateUserFacade extends AbstractValidateUserFacade {

    public ScannerValidateUserFacade(AuthService authService) {
        super(authService);
    }

    @Override
    public ReadableData getReadableData() {
        return new CLIReadData();
    }

    @Override
    public Iterator<String> getIterator() {
        return new Scanner(System.in);
    }

}
