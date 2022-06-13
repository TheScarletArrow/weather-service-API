package com.example.labapi;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: auth.proto")
public final class AuthServiceGrpc12 {

  private AuthServiceGrpc12() {}

  public static final String SERVICE_NAME = "auth.AuthService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Auth12.RequestAuth,
      Auth12.ResponseAuth> getAuthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Auth",
      requestType = Auth12.RequestAuth.class,
      responseType = Auth12.ResponseAuth.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Auth12.RequestAuth,
      Auth12.ResponseAuth> getAuthMethod() {
    io.grpc.MethodDescriptor<Auth12.RequestAuth, Auth12.ResponseAuth> getAuthMethod;
    if ((getAuthMethod = AuthServiceGrpc12.getAuthMethod) == null) {
      synchronized (AuthServiceGrpc12.class) {
        if ((getAuthMethod = AuthServiceGrpc12.getAuthMethod) == null) {
          AuthServiceGrpc12.getAuthMethod = getAuthMethod =
              io.grpc.MethodDescriptor.<Auth12.RequestAuth, Auth12.ResponseAuth>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Auth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Auth12.RequestAuth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Auth12.ResponseAuth.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("Auth"))
              .build();
        }
      }
    }
    return getAuthMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthServiceStub newStub(io.grpc.Channel channel) {
    return new AuthServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AuthServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AuthServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class AuthServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void auth(Auth12.RequestAuth request,
                     io.grpc.stub.StreamObserver<Auth12.ResponseAuth> responseObserver) {
      asyncUnimplementedUnaryCall(getAuthMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAuthMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Auth12.RequestAuth,
                Auth12.ResponseAuth>(
                  this, METHODID_AUTH)))
          .build();
    }
  }

  /**
   */
  public static final class AuthServiceStub extends io.grpc.stub.AbstractStub<AuthServiceStub> {
    private AuthServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AuthServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthServiceStub(channel, callOptions);
    }

    /**
     */
    public void auth(Auth12.RequestAuth request,
                     io.grpc.stub.StreamObserver<Auth12.ResponseAuth> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuthMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AuthServiceBlockingStub extends io.grpc.stub.AbstractStub<AuthServiceBlockingStub> {
    private AuthServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AuthServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Auth12.ResponseAuth auth(Auth12.RequestAuth request) {
      return blockingUnaryCall(
          getChannel(), getAuthMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AuthServiceFutureStub extends io.grpc.stub.AbstractStub<AuthServiceFutureStub> {
    private AuthServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AuthServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Auth12.ResponseAuth> auth(
        Auth12.RequestAuth request) {
      return futureUnaryCall(
          getChannel().newCall(getAuthMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AUTH = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AuthServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AuthServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTH:
          serviceImpl.auth((Auth12.RequestAuth) request,
              (io.grpc.stub.StreamObserver<Auth12.ResponseAuth>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Auth12.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AuthService");
    }
  }

  private static final class AuthServiceFileDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier {
    AuthServiceFileDescriptorSupplier() {}
  }

  private static final class AuthServiceMethodDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AuthServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AuthServiceGrpc12.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthServiceFileDescriptorSupplier())
              .addMethod(getAuthMethod())
              .build();
        }
      }
    }
    return result;
  }
}
