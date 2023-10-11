package org.example;

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
        System.out.println(helloServiceBlockingStub.withDeadlineAfter(200L, TimeUnit.MILLISECONDS).sayHello(hr));
    }
}