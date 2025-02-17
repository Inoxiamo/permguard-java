package com.permguard.pep.internal.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * V1PDPService	is the service for the Policy Decision Point.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: proto/v1/authorization_check.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class V1PDPServiceGrpc {

  private V1PDPServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "policydecisionpoint.V1PDPService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest,
      com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse> getAuthorizationCheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AuthorizationCheck",
      requestType = com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest.class,
      responseType = com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest,
      com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse> getAuthorizationCheckMethod() {
    io.grpc.MethodDescriptor<com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest, com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse> getAuthorizationCheckMethod;
    if ((getAuthorizationCheckMethod = V1PDPServiceGrpc.getAuthorizationCheckMethod) == null) {
      synchronized (V1PDPServiceGrpc.class) {
        if ((getAuthorizationCheckMethod = V1PDPServiceGrpc.getAuthorizationCheckMethod) == null) {
          V1PDPServiceGrpc.getAuthorizationCheckMethod = getAuthorizationCheckMethod =
              io.grpc.MethodDescriptor.<com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest, com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AuthorizationCheck"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new V1PDPServiceMethodDescriptorSupplier("AuthorizationCheck"))
              .build();
        }
      }
    }
    return getAuthorizationCheckMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static V1PDPServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<V1PDPServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<V1PDPServiceStub>() {
        @java.lang.Override
        public V1PDPServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new V1PDPServiceStub(channel, callOptions);
        }
      };
    return V1PDPServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static V1PDPServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<V1PDPServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<V1PDPServiceBlockingStub>() {
        @java.lang.Override
        public V1PDPServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new V1PDPServiceBlockingStub(channel, callOptions);
        }
      };
    return V1PDPServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static V1PDPServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<V1PDPServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<V1PDPServiceFutureStub>() {
        @java.lang.Override
        public V1PDPServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new V1PDPServiceFutureStub(channel, callOptions);
        }
      };
    return V1PDPServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * V1PDPService	is the service for the Policy Decision Point.
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void authorizationCheck(com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest request,
        io.grpc.stub.StreamObserver<com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAuthorizationCheckMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service V1PDPService.
   * <pre>
   * V1PDPService	is the service for the Policy Decision Point.
   * </pre>
   */
  public static abstract class V1PDPServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return V1PDPServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service V1PDPService.
   * <pre>
   * V1PDPService	is the service for the Policy Decision Point.
   * </pre>
   */
  public static final class V1PDPServiceStub
      extends io.grpc.stub.AbstractAsyncStub<V1PDPServiceStub> {
    private V1PDPServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected V1PDPServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new V1PDPServiceStub(channel, callOptions);
    }

    /**
     */
    public void authorizationCheck(com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest request,
        io.grpc.stub.StreamObserver<com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAuthorizationCheckMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service V1PDPService.
   * <pre>
   * V1PDPService	is the service for the Policy Decision Point.
   * </pre>
   */
  public static final class V1PDPServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<V1PDPServiceBlockingStub> {
    private V1PDPServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected V1PDPServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new V1PDPServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse authorizationCheck(com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAuthorizationCheckMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service V1PDPService.
   * <pre>
   * V1PDPService	is the service for the Policy Decision Point.
   * </pre>
   */
  public static final class V1PDPServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<V1PDPServiceFutureStub> {
    private V1PDPServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected V1PDPServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new V1PDPServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse> authorizationCheck(
        com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAuthorizationCheckMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AUTHORIZATION_CHECK = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTHORIZATION_CHECK:
          serviceImpl.authorizationCheck((com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest) request,
              (io.grpc.stub.StreamObserver<com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getAuthorizationCheckMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest,
              com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse>(
                service, METHODID_AUTHORIZATION_CHECK)))
        .build();
  }

  private static abstract class V1PDPServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    V1PDPServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.permguard.pep.internal.proto.AuthorizationCheck.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("V1PDPService");
    }
  }

  private static final class V1PDPServiceFileDescriptorSupplier
      extends V1PDPServiceBaseDescriptorSupplier {
    V1PDPServiceFileDescriptorSupplier() {}
  }

  private static final class V1PDPServiceMethodDescriptorSupplier
      extends V1PDPServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    V1PDPServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (V1PDPServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new V1PDPServiceFileDescriptorSupplier())
              .addMethod(getAuthorizationCheckMethod())
              .build();
        }
      }
    }
    return result;
  }
}
