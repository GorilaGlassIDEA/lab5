package by.dima.model.telegram;

import com.example.grpc.TelegramBotExchangeMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestManager {

    public TelegramBotExchangeMessage.ClientLayerResponse answerOnUserRequest(TelegramBotExchangeMessage.UserRequest userRequest) {
        return TelegramBotExchangeMessage.ClientLayerResponse.newBuilder()
                .setAnswer("Тестовый ответ для порверки корректности работы")
                .build();
    }

}
