package org.example.presentation.grpc;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
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
        System.out.println(HelloInterceptor.HELLO_MSG.get());
        Hello hello = helloService.generateHello();

        Metadata trails = new Metadata();
        trails.put(Metadata.Key.of("errCode", Metadata.ASCII_STRING_MARSHALLER), "5678");
        trails.put(Metadata.Key.of("errMsg", Metadata.ASCII_STRING_MARSHALLER), "A custom error");
        StatusRuntimeException ex = new StatusRuntimeException(Status.INTERNAL, trails);
        responseObserver.onError(ex);
//        HelloReply reply = HelloReply.newBuilder()
//                .setMessage(hello.getMessage())
//                .build();
//        try {
//            Thread.sleep(100L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        responseObserver.onNext(reply);
//        responseObserver.onCompleted();
//        System.out.println("x count: " + x.size());
//        System.out.println(x);
    }
}
