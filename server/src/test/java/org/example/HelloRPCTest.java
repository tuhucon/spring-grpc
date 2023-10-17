package org.example;

import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.grpc.model.HelloReply;
import org.example.grpc.model.HelloRequest;
import org.example.grpc.model.HelloServiceGrpc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest(properties = {
        "grpc.server.inProcessName=test", // Enable inProcess server
        "grpc.server.port=-1", // Disable external server
        "grpc.client.GLOBAL.address=in-process:test" // Configure the client to connect to the inProcess server
})
@DirtiesContext
public class HelloRPCTest {

    @GrpcClient("helloService")
    HelloServiceGrpc.HelloServiceBlockingStub helloService;


    @Test
    @DirtiesContext
    public void testHello() {
        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setName("tu hu con")
                .build();
        Assertions.assertThrows(StatusRuntimeException.class, () -> helloService.sayHello(helloRequest));
    }
}
