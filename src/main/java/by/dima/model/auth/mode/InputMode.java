package by.dima.model.auth.mode;

import by.dima.model.auth.AuthUtils;
import by.dima.model.auth.read.data.CLIReadData;
import by.dima.model.auth.read.data.ReadUserData;
import by.dima.model.auth.AuthService;
import by.dima.model.auth.read.data.ReadableData;
import by.dima.model.common.UserModel;

import java.util.Scanner;

public class InputMode {
    private UserModel userModel = new UserModel();
    private final Scanner scanner;
    private final AuthService authService;
    private final ReadableData readableData;

    public InputMode(AuthService authService, ReadableData readableData) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
        this.readableData = readableData;
    }

    public UserModel getAnswer() {
        ReadUserData readUserData = new ReadUserData(readableData);
        while (scanner.hasNextLine()) {
            String mode = scanner.nextLine();
            try {
                Long longMode = Long.parseLong(mode);
                if (longMode == 1) {
                    userModel = AuthUtils.authorizedStatusControl(readUserData, authService);
                    break;
                } else if (longMode == 0) {
                    userModel = AuthUtils.authenticationStatusControl(readUserData, authService);
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
