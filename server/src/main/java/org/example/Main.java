package org.example;

import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
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
            if (serverBuilder instanceof NettyServerBuilder nettyServerBuilder) {
                nettyServerBuilder.executor(Executors.newVirtualThreadPerTaskExecutor());
                nettyServerBuilder.maxConcurrentCallsPerConnection(200);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}