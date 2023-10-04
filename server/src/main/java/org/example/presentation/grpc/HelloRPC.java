package org.example.presentation.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.appcore.appservice.HelloService;
import org.example.appcore.domain.Hello;
import org.example.grpc.model.HelloReply;
import org.example.grpc.model.HelloRequest;
import org.example.grpc.model.HelloServiceGrpc;

@GrpcService
@RequiredArgsConstructor
public class HelloRPC extends HelloServiceGrpc.HelloServiceImplBase {

    private final HelloService helloService;

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        Hello hello = helloService.generateHello();

        HelloReply reply = HelloReply.newBuilder()
                .setMessage(hello.getMessage())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
