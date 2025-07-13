package by.dima.model.telegram;

import by.dima.model.Client;
import by.dima.model.client.ClientController;
import by.dima.model.commands.CommandManager;
import by.dima.model.common.AnswerDTO;
import com.example.grpc.TelegramBotExchangeMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestManager {

    private final ClientController controller;

    public RequestManager(ClientController controller) {
        this.controller = controller;
    }

    public TelegramBotExchangeMessage.ClientLayerResponse answerOnUserRequest(TelegramBotExchangeMessage.UserRequest userRequest) {
        AnswerDTO answerDTO = controller.sendCommandReceiveAnswer(userRequest.getMessage());
        return TelegramBotExchangeMessage.ClientLayerResponse.newBuilder()
                .setAnswer(answerDTO.getAnswer())
                .setTelegramId(userRequest.getTelegramId())
                .build();
    }

}
