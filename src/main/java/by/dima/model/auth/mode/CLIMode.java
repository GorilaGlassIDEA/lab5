package by.dima.model.auth.mode;

import by.dima.model.Main;
import by.dima.model.auth.AuthScanner;
import by.dima.model.auth.AuthService;
import by.dima.model.common.UserModel;

import java.util.Scanner;

public class CLIMode implements ChooseAuthMode {
    private final Scanner scanner;
    private UserModel userModel = new UserModel();
    private final AuthScanner authScanner;
    private final AuthService authService;

    public CLIMode(Scanner scanner, AuthService authService) {
        this.scanner = scanner;
        this.authScanner = new AuthScanner(scanner);
        this.authService = authService;
    }

    public UserModel getAnswer() {
        while (scanner.hasNextLine()) {
            String mode = scanner.nextLine();
            try {
                Long longMode = Long.parseLong(mode);
                if (longMode == 1) {
                    userModel = Main.authorizedStatusControl(authScanner, authService);
                    break;
                } else if (longMode == 0) {
                    userModel = Main.authenticationStatusControl(authScanner, authService);
                    break;
                } else {
                    System.out.println("Некорректный ввод!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод!");
            }
        }
        return userModel;
    }
}
