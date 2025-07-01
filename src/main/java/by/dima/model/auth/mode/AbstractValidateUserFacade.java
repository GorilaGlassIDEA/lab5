package by.dima.model.auth.mode;

import by.dima.model.auth.AuthService;
import by.dima.model.auth.ControlAuthStatusService;
import by.dima.model.auth.read.data.ReadableData;
import by.dima.model.common.UserModel;

import java.util.Iterator;

public abstract class AbstractValidateUserFacade implements GetableValidateUserModel {

    private final Iterator<String> iterator;
    private final ControlAuthStatusService authStatusService;

    public AbstractValidateUserFacade(Iterator<String> iterator, AuthService authService) {
        this.iterator = iterator;
        this.authStatusService = new ControlAuthStatusService(getReadableData(), authService);
    }

    @Override
    public UserModel getValidateUser() {
        UserModel userModel = new UserModel();
        while (iterator.hasNext()) {
            String mode = iterator.next();
            try {
                Long longMode = Long.parseLong(mode);
                if (longMode == 1) {
                    userModel = authStatusService.authorizedStatusControl();
                    break;
                } else if (longMode == 0) {
                    userModel = authStatusService.authenticationStatusControl();
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

    public abstract ReadableData getReadableData();
}
