package by.dima.model.telegram;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

class GreetingService extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(
            GreetingServiceOuterClass.HelloRequest request,
            StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        System.out.println("Пришел запрос " + request);

        GreetingServiceOuterClass.HelloResponse helloResponse = GreetingServiceOuterClass.HelloResponse
                .newBuilder()
                .setGreeting("Hello from server!")
                .build();

        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }

}
