package by.dima.model.auth;


import java.util.Scanner;

public class AuthScanner {

    private String username;
    private String password;
    private final ScannerService scannerService;
    private boolean registerFlag = false;

    public AuthScanner(Scanner scanner) {
        this.scannerService = new ScannerService(scanner);
    }

    public void setData() {
        if (username == null && password == null) {
            System.out.println("------Вход в систему------");
            username = scannerService.getString("Введите логин",
                    "Некорректный ввод, попробуйте еще раз!");
            password = scannerService.getString("Введите пароль для логина",
                    "Некорректный ввод, попробуйте еще раз!");
            if (username != null && password != null) registerFlag = true;
        }
    }

    public String getUsername() {
        if (registerFlag) {
            return username;
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
