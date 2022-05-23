package com.example.labapi.api.grpc;

import com.example.labapi.api.Auth;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.lettuce.core.AbstractRedisAsyncCommands;
import lombok.var;

public class GrpcClient {

    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5000)
            .usePlaintext()
            .build();


    var stub = AuthServiceGrpc.newBlockingStub(channel);
    Auth.ResponseAuth responseAuth = stub.auth(
            Auth.RequestAuth.newBuilder()
                    .setName("Anton")
    )


}
