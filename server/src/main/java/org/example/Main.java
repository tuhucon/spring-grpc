package org.example;

import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;

@SpringBootApplication
public class Main {

    @Bean
    public GrpcServerConfigurer getGrpcServerConfigurer() {
        return serverBuilder -> {
            serverBuilder.executor(Executors.newVirtualThreadPerTaskExecutor());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}