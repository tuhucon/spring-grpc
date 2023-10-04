package org.example.presentation.grpc;

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
