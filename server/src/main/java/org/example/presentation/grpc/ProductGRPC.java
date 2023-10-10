package org.example.presentation.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.appcore.appservice.ProductService;
import org.example.appcore.domain.Product;
import org.example.grpc.model.ProductIdRequest;
import org.example.grpc.model.ProductRequest;
import org.example.grpc.model.ProductServiceGrpc;

@GrpcService
@RequiredArgsConstructor
public class ProductGRPC extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductService productService;

    @Override
    public StreamObserver<ProductRequest> countProduct(StreamObserver<Int32Value> responseObserver) {
        return new StreamObserver<ProductRequest>() {
            int count = 0;

            @Override
            public void onNext(ProductRequest value) {
                System.out.println(Thread.currentThread());
                count++;
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(Int32Value.of(count));
                responseObserver.onCompleted();
            }
        };

    }

    @Override
    public void getAllProduct(Empty request, StreamObserver<ProductRequest> responseObserver) {
        for (int i = 0; i < 10; i++) {
            responseObserver.onNext(ProductRequest.newBuilder()
                    .setId("id: " + i)
                    .setName("name: " + i)
                    .setDescription("description: " + i)
                    .build()
            );
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getProduct(ProductIdRequest request, StreamObserver<ProductRequest> responseObserver) {

        Product p = productService.getProduct(request.getId());
        ProductRequest pr = ProductRequest.newBuilder()
                .setId(p.getId())
                .setName(p.getName())
                .setDescription(p.getDescription())
                .build();
        responseObserver.onNext(pr);
        responseObserver.onCompleted();
    }

    @Override
    public void addProduct(ProductRequest request, StreamObserver<ProductRequest> responseObserver) {
        Product p = productService.addProduct(request.getName(), request.getDescription());
        ProductRequest pr = ProductRequest.newBuilder()
                .setId(p.getId())
                .setName(p.getName())
                .setDescription(p.getDescription())
                .build();
        responseObserver.onNext(pr);
        responseObserver.onCompleted();
    }
}
