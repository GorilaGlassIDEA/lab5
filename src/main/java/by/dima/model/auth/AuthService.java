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
        AuthRequestDTO authRequestDTO = new AuthRequestDTO(userModel);
        authRequestDTO.setAuthList(AuthList.GET_STATUS);
        AnswerDTO answerDTO = requestFacade.getAnswer(authRequestDTO);
        logger.log(Level.INFO, "Текущий статус пользователя: " + answerDTO.getAuth());
        return answerDTO.getAuth();
    }

    public AuthRequestDTO authentication(UserModel userModel) {
        AnswerDTO answerDTO;
        AuthRequestDTO authRequestDTO = new AuthRequestDTO(userModel);
        authRequestDTO.setAuthList(AuthList.REQUEST_REGISTER);
        answerDTO = requestFacade.getAnswer(authRequestDTO);
        authRequestDTO.setAuthList(answerDTO.getAuth());
        if (authRequestDTO.getAuthList() == AuthList.IS_EXIST) {
            System.out.println("Пользователь с таким логином уже существует!");
        }
        if (authRequestDTO.getAuthList() == AuthList.UNAUTHORIZED) {
            System.out.println("Пользователь успешно создан!");
        }
        return authRequestDTO;
    }

    public AuthRequestDTO authorization(UserModel userModel) {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO(userModel);
        authRequestDTO.setAuthList(AuthList.UNAUTHORIZED);
        AnswerDTO answerDTO = requestFacade.getAnswer(authRequestDTO);
        authRequestDTO.setAuthList(answerDTO.getAuth());

        if (authRequestDTO.getAuthList() == AuthList.AUTHORIZATION) {
            System.out.println(answerDTO.getAnswer());
        } else if (authRequestDTO.getAuthList() == AuthList.IS_EXIST || authRequestDTO.getAuthList() == AuthList.UNAUTHORIZED) {
            authRequestDTO.setAuthList(AuthList.UNAUTHORIZED);
            answerDTO = requestFacade.getAnswer(authRequestDTO);
            authRequestDTO.setAuthList(answerDTO.getAuth());
            System.out.println(answerDTO.getAnswer());
        } else {
            System.out.println(answerDTO.getAnswer());
        }

        return authRequestDTO;
    }
}
