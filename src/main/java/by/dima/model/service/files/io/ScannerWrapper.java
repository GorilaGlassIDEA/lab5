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
        String nextLine = scanner.nextLine().trim();
        return nextLine.split(" ");
    }

    public boolean getScannerStatus() {
        try {
            return scanner.hasNextLine();
        } catch (IllegalStateException e) {
            return false;
        }
    }

    public void closeScanner() {
        scanner.close();
    }

}
