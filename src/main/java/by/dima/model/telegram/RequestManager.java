package by.dima.model.telegram;

import com.example.grpc.GreetingServiceOuterClass;
import com.example.grpc.TelegramBotExchangeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RequestManager {

    @Deprecated
    public GreetingServiceOuterClass.HelloResponse execute(GreetingServiceOuterClass.HelloRequest request) {

        GreetingServiceOuterClass.HelloResponse helloResponse = GreetingServiceOuterClass.HelloResponse
                .newBuilder()
                .setGreeting("Клиентский слой получил команду от телеграм бота: " + request)
                .build();

        log.info("Пришло сообщение от телеграм бота: " + request);

        //TODO: обработать все команды от telegramBot
        return helloResponse;
    }

    public TelegramBotExchangeMessage.ClientLayerResponse executeUserRequest(TelegramBotExchangeMessage.UserRequest userRequest) {
        TelegramBotExchangeMessage.ClientLayerResponse response = TelegramBotExchangeMessage.ClientLayerResponse.newBuilder()
                .setAnswer("Тестовый ответ для порверки корректности работы")
                .build();
        return response;
    }

}
