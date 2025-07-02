package by.dima.model.auth.mode;

import by.dima.model.auth.AuthService;
import by.dima.model.auth.ControlAuthStatusService;
import by.dima.model.auth.read.ReadableData;
import by.dima.model.common.UserModel;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

@Slf4j
abstract class AbstractValidateUserFacade implements GetableValidateUserModel {

    private final Iterator<String> iterator;
    private final ControlAuthStatusService authStatusService;

    AbstractValidateUserFacade(AuthService authService) {
        this.iterator = getIterator();
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
                    log.info("Нельзя вводить другие числа!");
                }
            } catch (NumberFormatException e) {
                log.info("Некорректный ввод числа!");
            }
        }
        return userModel;
    }

    public abstract ReadableData getReadableData();

    public abstract Iterator<String> getIterator();
}
