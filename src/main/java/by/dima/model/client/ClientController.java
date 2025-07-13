package by.dima.model.client;

import by.dima.model.commands.CommandManager;
import by.dima.model.commands.model.Command;
import by.dima.model.common.*;
import by.dima.model.client.parser.ForDeserializableAnswerDTO;
import by.dima.model.client.parser.ForSerializableObject;
import by.dima.model.client.request.Clientable;
import by.dima.model.util.util.GetSecondArgFromArgsUtil;

import lombok.Getter;
import lombok.Setter;


import java.util.logging.*;

@Setter
@Getter
public class ClientController {
    private Logger logger;
    private Clientable clientRequestUDP;
    private AnswerDTO answerDTO;
    private ForSerializableObject<AuthRequestDTO> forSerializableObject;
    private ForDeserializableAnswerDTO<AnswerDTO> deserializableAnswerDTO;
    private CommandManager manager;

    private CommandDTO commandDTO;
    private final UserModel userModel;

    private final Long userId;
    private final AuthRequestDTO authorizationRequest;

    public ClientController(UserModel userModel, Logger logger, Clientable clientRequestUDP, ForSerializableObject<AuthRequestDTO> forSerializableObject, ForDeserializableAnswerDTO<AnswerDTO> deserializableAnswerDTO, CommandManager manager) {
        this.logger = logger;
        this.clientRequestUDP = clientRequestUDP;
        this.forSerializableObject = forSerializableObject;
        this.deserializableAnswerDTO = deserializableAnswerDTO;
        this.manager = manager;
        this.userId = clientRequestUDP.getUserId();
        this.userModel = userModel;
        this.authorizationRequest = AuthRequestDTO.getInstance(userModel);
    }

    public AnswerDTO sendCommandReceiveAnswer(String commandString) throws RuntimeException {
        String commandStringClean = GetSecondArgFromArgsUtil.getFirstArg(commandString);
        String commandArg = GetSecondArgFromArgsUtil.getSecondArg(commandString);

        if (manager.getCommandMap().containsKey(commandStringClean)) {
            Command command = manager.getCommandMap().get(commandStringClean);
            if (!commandArg.isBlank()) {
                command.setArgs(commandArg);
            }
            commandDTO = manager.execute(command);

            // добавляем в объект с авторизацией ссылку на CommandDTO
            authorizationRequest.setCommandDTO(commandDTO);
            authorizationRequest.setAuthList(AuthList.AUTHORIZATION);
            logger.log(Level.INFO, "CommandDTO для отправки на сервер: " + authorizationRequest);
            try {
                clientRequestUDP.makePost(forSerializableObject.serial(authorizationRequest));
                answerDTO = deserializableAnswerDTO.deserial(clientRequestUDP.makeGet());
            } catch (NullPointerException e) {
                throw new RuntimeException();
            }
            return answerDTO;
        } else {
            return new AnswerDTO("Не удалось найти команду с именем: " + commandStringClean);
        }
    }

}
