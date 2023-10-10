package org.example.presentation.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.appcore.appservice.HelloService;
import org.example.appcore.domain.Hello;
import org.example.grpc.model.HelloReply;
import org.example.grpc.model.HelloRequest;
import org.example.grpc.model.HelloServiceGrpc;

import java.util.HashSet;
import java.util.Set;

@GrpcService(interceptors = HelloInterceptor.class)
@RequiredArgsConstructor
public class HelloRPC extends HelloServiceGrpc.HelloServiceImplBase {

    Set<String> x = new HashSet<>();

    private final HelloService helloService;

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        x.add(Thread.currentThread().toString());
        System.out.println(Thread.currentThread().toString());
        Hello hello = helloService.generateHello();

        HelloReply reply = HelloReply.newBuilder()
                .setMessage(hello.getMessage())
                .build();
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        System.out.println("x count: " + x.size());
        System.out.println(x);
    }
}
