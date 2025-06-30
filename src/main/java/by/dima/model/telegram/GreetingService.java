package by.dima.model.telegram;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Slf4j
class GreetingService extends GreetingServiceGrpc.GreetingServiceImplBase {
    private final RequestManager requestManager;

    public GreetingService(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @Override
    public void greeting(
            GreetingServiceOuterClass.HelloRequest request,
            StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {

        GreetingServiceOuterClass.HelloResponse helloResponse = requestManager.execute(request);

        log.info(helloResponse.toString());

        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }

}
