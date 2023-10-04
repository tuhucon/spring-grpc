package org.example.appcore.appservice;

import org.example.appcore.domain.Hello;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public Hello generateHello() {
        return new Hello("hello world");
    }
}
