package by.dima.model.auth.read.impl;

import by.dima.model.auth.read.ReadableData;

import java.util.Scanner;

public class CLIReadData implements ReadableData {
    private final Scanner scanner;

    public CLIReadData() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getString(String openMessage, String repeatMessage) {
        System.out.println(openMessage);
        while (true) {
            if (scanner.hasNextLine()) {
                String thisLine = scanner.nextLine();
                if (!thisLine.isBlank()) {
                    return thisLine;
                } else {
                    System.out.println(repeatMessage);
                }
            }
        }
    }


}
