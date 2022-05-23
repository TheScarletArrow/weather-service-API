package com.example.labapi.api.grpc;

import com.example.labapi.api.Auth;
import io.grpc.stub.StreamObserver;

public class AuthService  {
    public void auth(
            Auth.RequestAuth requestAuth, StreamObserver<Auth.ResponseAuth> responseAuthStreamObserver
    ){
        String name = "name" +
                requestAuth.getName() +
                " ";

        Auth.ResponseAuth responseAuth =  Auth.ResponseAuth.newBuilder().setAuthenticated(true).build();

        responseAuthStreamObserver.onNext(responseAuth);
        responseAuthStreamObserver.onCompleted();
    }

}
