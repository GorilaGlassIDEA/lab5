package by.dima.model.auth;


import by.dima.model.common.AuthList;
import by.dima.model.common.UserModel;
import lombok.Data;

import java.util.Scanner;

@Data
public class AuthScanner {

    private UserModel userModel;
    private final ScannerService scannerService;

    public AuthScanner(Scanner scanner) {
        this.scannerService = new ScannerService(scanner);
    }

    public UserModel inputUserDataFromKeyboard() {
        userModel = new UserModel();
        userModel.setUsername(scannerService.getString("Введите логин",
                "Некорректный ввод, попробуйте еще раз!"));
        userModel.setPassword(scannerService.getString("Введите пароль для логина",
                "Некорректный ввод, попробуйте еще раз!"));
        return userModel;
    }


}
