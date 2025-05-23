package by.dima.model.auth;

import java.util.Scanner;

public class ScannerService {
    private final Scanner scanner;

    public ScannerService(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getString(String openMessage, String repeatMessage) {
        System.out.println(openMessage);
        while (true) {
            if (scanner.hasNextLine()) {
                String thisLine = scanner.nextLine();
                if (!thisLine.isBlank()) {
                    return thisLine;
                }
                else {
                    System.out.println(repeatMessage);
                }
            }
        }
    }
}
