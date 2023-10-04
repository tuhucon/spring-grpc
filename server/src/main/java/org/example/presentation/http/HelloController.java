package org.example.presentation.http;

import lombok.RequiredArgsConstructor;
import org.example.appcore.appservice.HelloService;
import org.example.appcore.domain.Hello;
import org.example.grpc.model.HelloReply;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @GetMapping("/hello")
    public HelloReplyDTO hello() {
        Hello hello = helloService.generateHello();
        return new HelloReplyDTO(hello.getMessage());
    }
}
