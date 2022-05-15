package com.example.labapi.api.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient{
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5000)
                .usePlaintext()
                .build();

//        RequestAuth.HelloServiceBlockingStub stub
//                = RequestAuth.newBlockingStub(channel);
//
//        ResponseAuth helloResponse = stub.hello(RequestAuth.newBuilder()
//                .setName("Anton")
//                .build());



    }

}
