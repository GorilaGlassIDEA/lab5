package by.dima.model.service.files.io;

import lombok.Getter;

import java.util.Scanner;

@Getter
public class ScannerWrapper {
    private final Scanner scanner;

    public ScannerWrapper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] newLine() {
        return scanner.nextLine().split(" ");
    }

    public boolean getScannerStatus() {
        try {
            return scanner.hasNext();
        } catch (IllegalStateException e) {
            return false;
        }
    }

    public void closeScanner() {
        scanner.close();
    }

}
