package com.example.labapi.GRPC;


import com.example.labapi.Auth;
import com.example.labapi.AuthServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {


    public static boolean getAuthenticated(String name) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("grpc", 5000)
                .usePlaintext()
                .build();
        AuthServiceGrpc.AuthServiceBlockingStub stub = AuthServiceGrpc.newBlockingStub(channel);

        Auth.ResponseAuth responseAuth = stub.auth(Auth.RequestAuth.newBuilder()
                .setName(name)
                .build());
        final boolean authenticated = responseAuth.getAuthenticated();
        channel.shutdown();
        return authenticated;

    }
    }
