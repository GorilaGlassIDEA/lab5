package by.dima.model.auth.mode;

import by.dima.model.auth.AuthService;
import by.dima.model.auth.read.ReadableData;

import java.util.Iterator;

public class TelegramValidateUserFacade extends AbstractValidateUserFacade {


    public TelegramValidateUserFacade(AuthService authService) {
        super(authService);
    }

    @Override
    public ReadableData getReadableData() {
        return null;
    }

    @Override
    public Iterator<String> getIterator() {
        return null;
    }


}
