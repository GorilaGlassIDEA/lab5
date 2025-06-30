package by.dima.model.telegram;

import com.example.grpc.TelegramBotExchangeMessage;
import com.example.grpc.TelegramBotServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
public class MessageExchangeService extends TelegramBotServiceGrpc.TelegramBotServiceImplBase {
    private final RequestManager requestManager;

    public MessageExchangeService(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @Override
    public void getResponse(TelegramBotExchangeMessage.UserRequest request,
                            StreamObserver<TelegramBotExchangeMessage.ClientLayerResponse> responseObserver) {

        log.info("От телеграм бота пришло сообщение: " + request);

        TelegramBotExchangeMessage.ClientLayerResponse response = requestManager.executeUserRequest(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
