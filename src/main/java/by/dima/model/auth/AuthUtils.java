package by.dima.model.auth;

import by.dima.model.auth.read.data.ReadUserData;
import by.dima.model.common.AuthList;
import by.dima.model.common.AuthRequestDTO;
import by.dima.model.common.UserModel;
import by.dima.model.util.PasswordHasher;

public class AuthUtils {
    public static UserModel authorizedStatusControl(ReadUserData readUserData, AuthService authService) {
        UserModel userModel = readUserData.read();
        userModel.setPassword(userModel.getPassword());
        AuthRequestDTO authRequestDTO = authService.authorization(userModel);
        while (authRequestDTO.getAuthList() != AuthList.AUTHORIZATION) {
            userModel = readUserData.read();
            userModel.setPassword(PasswordHasher.hashPasswordSHA1(userModel.getPassword()));
            authRequestDTO = authService.authorization(userModel);
        }
        System.out.println("Класс Main метод авторизации" + authRequestDTO);
        return userModel;
    }

    public static UserModel authenticationStatusControl(ReadUserData authScanner, AuthService authService) {
        UserModel userModel = authScanner.read();
        userModel.setPassword(userModel.getPassword());
        AuthRequestDTO authRequestDTO = authService.authentication(userModel);

        while (authRequestDTO.getAuthList() != AuthList.AUTHORIZATION) {
            userModel = authScanner.read();
            userModel.setPassword(PasswordHasher.hashPasswordSHA1(userModel.getPassword()));
            authRequestDTO = authService.authentication(userModel);
        }
        return userModel;
    }
}
