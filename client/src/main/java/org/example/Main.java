package org.example;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.grpc.model.HelloRequest;
import org.example.grpc.model.HelloServiceGrpc;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GrpcClient("helloService")
    HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;


    @Override
    public void run(String... args) throws Exception {
        HelloRequest hr = HelloRequest.newBuilder()
                .setName("tu hu con")
                .build();
        try {
            helloServiceBlockingStub.withDeadlineAfter(2000L, TimeUnit.MILLISECONDS).sayHello(hr);
        } catch (StatusRuntimeException t) {
            System.out.println(t.getMessage());
            System.out.println(t.getTrailers());
            System.out.println(t.getCause());
        }
    }
}