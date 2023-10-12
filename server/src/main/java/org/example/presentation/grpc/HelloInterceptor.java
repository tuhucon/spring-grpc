package org.example.presentation.grpc;

import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import org.springframework.stereotype.Component;

@Component
public class HelloInterceptor implements ServerInterceptor {

    public static Context.Key<String> HELLO_MSG = Context.key("HELLO_MSG");

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        System.out.println("Hello Interceptor in thread: " + Thread.currentThread());
        Context context = Context.current().withValue(HELLO_MSG, "hello world from context");
        return Contexts.interceptCall(context, call, headers, next);
    }
}
