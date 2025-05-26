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

    public AuthRequestDTO authentication(UserModel userModel) {
        AnswerDTO answerDTO;
        AuthRequestDTO authRequestDTO = new AuthRequestDTO(userModel);
        if (getClientStatus(userModel) == AuthList.UNAUTHENTICATED) {
            authRequestDTO.setAuthenticated(false);
            answerDTO = requestFacade.getAnswer(authRequestDTO);
            if (answerDTO.getAuth() != AuthList.UNAUTHENTICATED) {
                authRequestDTO.setAuthenticated(true);
                System.out.println("Регистрация выполнена успешно!");
            }
        }
        //TODO: потенциальный предвестник проблем с регистрацией проверить код если возникнут ошибки!
        return authRequestDTO;
    }

}
