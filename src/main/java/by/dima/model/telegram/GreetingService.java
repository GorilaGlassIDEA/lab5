package by.dima.model.telegram;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class GreetingService extends GreetingServiceGrpc.GreetingServiceImplBase {
    private final RequestManager requestManager;

    @Autowired
    public GreetingService(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @Override
    public void greeting(
            GreetingServiceOuterClass.HelloRequest request,
            StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        responseObserver.onNext(requestManager.execute(request));
        responseObserver.onCompleted();
    }

}
