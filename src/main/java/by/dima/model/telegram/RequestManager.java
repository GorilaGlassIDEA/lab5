package by.dima.model.telegram;

import com.example.grpc.GreetingServiceOuterClass;
import com.example.grpc.TelegramBotExchangeMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestManager {

    public GreetingServiceOuterClass.HelloResponse execute(GreetingServiceOuterClass.HelloRequest request) {
        return GreetingServiceOuterClass.HelloResponse
                .newBuilder()
                .setGreeting("Клиентский слой получил команду от телеграм бота: " + request)
                .build();
    }

    public TelegramBotExchangeMessage.ClientLayerResponse executeUserRequest(TelegramBotExchangeMessage.UserRequest userRequest) {
        return TelegramBotExchangeMessage.ClientLayerResponse.newBuilder()
                .setAnswer("Тестовый ответ для порверки корректности работы")
                .build();
    }

}
