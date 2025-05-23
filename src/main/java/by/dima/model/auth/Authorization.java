package by.dima.model.auth;

import java.util.Scanner;

public class Authorization {
    private final Scanner scanner;
    private String username;
    private String name;
    private String password;

    public Authorization(Scanner scanner) {
        this.scanner = scanner;
    }
}
