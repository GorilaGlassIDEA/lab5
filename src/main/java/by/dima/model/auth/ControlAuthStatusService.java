package by.dima.model.auth;

import by.dima.model.auth.read.ReadableData;
import by.dima.model.common.AuthList;
import by.dima.model.common.AuthRequestDTO;
import by.dima.model.common.UserModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ControlAuthStatusService {
    private final ReadableData readableData;
    private final AuthService authService;


    public ControlAuthStatusService(ReadableData readableData, AuthService authService) {
        this.readableData = readableData;
        this.authService = authService;
    }

    public UserModel authorizedStatusControl() {
        UserModel userModel = read();
        userModel.setPassword(userModel.getPassword());
        AuthRequestDTO authRequestDTO = authService.authorization(userModel);
        while (authRequestDTO.getAuthList() != AuthList.AUTHORIZATION) {
            userModel = read();
            userModel.setPassword(userModel.getPassword());
            authRequestDTO = authService.authorization(userModel);
        }
        log.info("Метод авторизации {}", authRequestDTO);
        return userModel;
    }

    public UserModel authenticationStatusControl() {
        UserModel userModel = read();
        userModel.setPassword(userModel.getPassword());
        AuthRequestDTO authRequestDTO = authService.authentication(userModel);

        while (authRequestDTO.getAuthList() != AuthList.AUTHORIZATION) {
            userModel = read();
            userModel.setPassword(userModel.getPassword());
            authRequestDTO = authService.authentication(userModel);
        }
        return userModel;
    }

    private UserModel read() {
        UserModel userModel = new UserModel();
        userModel.setUsername(readableData.getString("Введите логин",
                "Некорректный ввод, попробуйте еще раз!"));
        userModel.setPassword(readableData.getString("Введите пароль для логина",
                "Некорректный ввод, попробуйте еще раз!"));
        return userModel;
    }
}
