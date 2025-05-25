package by.dima.model.auth;

import by.dima.model.common.AnswerDTO;
import by.dima.model.common.AuthList;
import by.dima.model.common.AuthRequestDTO;
import by.dima.model.common.UserModel;
import by.dima.model.util.RequestFacade;
import by.dima.model.util.logger.factory.LoggerWrapper;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthService {

    private final Logger logger = LoggerWrapper.getLogger();
    private final RequestFacade requestFacade;


    public AuthService(RequestFacade requestFacade) {
        this.requestFacade = requestFacade;
    }

    public AuthList getClientStatus(UserModel userModel) throws NullPointerException {
        AnswerDTO answerDTO = requestFacade.getAnswer(new AuthRequestDTO(userModel));
        logger.log(Level.INFO, "Текущий статус пользователя: " + answerDTO.getAuth());
        return answerDTO.getAuth();
    }

}
