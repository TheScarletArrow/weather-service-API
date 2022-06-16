package com.example.labapi.api.grpc;


import com.example.labapi.Auth12;
import com.example.labapi.AuthServiceGrpc12;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {


    public static  boolean getAuthenticated(String name) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5000)
                .usePlaintext()
                .build();
        AuthServiceGrpc12.AuthServiceBlockingStub stub = AuthServiceGrpc12.newBlockingStub(channel);

        Auth12.ResponseAuth responseAuth = stub.auth(Auth12.RequestAuth.newBuilder()
                .setName(name)
                .build());
        channel.shutdown();
        return responseAuth.getAuthenticated();

    }
}
