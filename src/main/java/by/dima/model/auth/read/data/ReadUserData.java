package by.dima.model.auth.read.data;

import by.dima.model.common.UserModel;

public class ReadUserData {
    private final ReadableData readableData;

    public ReadUserData(ReadableData readableData) {
        this.readableData = readableData;
    }

    public UserModel read() {
        UserModel userModel = new UserModel();
        userModel.setUsername(readableData.getString("Введите логин",
                "Некорректный ввод, попробуйте еще раз!"));
        userModel.setPassword(readableData.getString("Введите пароль для логина",
                "Некорректный ввод, попробуйте еще раз!"));
        return userModel;
    }


}
