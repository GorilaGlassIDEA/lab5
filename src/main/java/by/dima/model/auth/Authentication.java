package by.dima.model.auth;

import java.util.Scanner;

public class Authentication {

    private String username;
    private String name;
    private String password;
    private ScannerService scannerService;
    private boolean registerFlag = false;

    public Authentication(Scanner scanner) {
        this.scannerService = new ScannerService(scanner);
    }

    public void setData() {
        if (username == null && name == null && password == null) {
            name = scannerService.getString("Введите свое имя",
                    "Некорректный ввод, попробуйте еще раз!");
            username = scannerService.getString("Введите логин",
                    "Некорректный ввод, попробуйте еще раз!");
            password = scannerService.getString("Введите пароль для логина",
                    "Некорректный ввод, попробуйте еще раз!");
            if (name != null && username != null && password != null) registerFlag = true;
        }
    }

    public String getUsername() {
        if (registerFlag) {
            return username;
        }
        return null;
    }

    public String getName() {
        if (registerFlag) {
            return name;
        }
        return null;
    }

    public String getPassword() {
        if (registerFlag) {
            return password;
        }
        return null;
    }
}
