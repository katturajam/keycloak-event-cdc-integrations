package com.kcl.event.grpc.listener;

import com.kcl.event.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.atomic.AtomicBoolean;


public class GrpcStreamManager {
    private static GrpcStreamManager instance;
    private final EventServiceGrpc.EventServiceStub asyncStub;
    private StreamObserver<EventRequest> requestObserver;
    private final AtomicBoolean isInitialized = new AtomicBoolean(false);

    private GrpcStreamManager() {
        String host = System.getenv().getOrDefault("GRPC_HOST", "localhost");
        int port = Integer.parseInt(System.getenv().getOrDefault("GRPC_PORT", "50051"));

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        asyncStub = EventServiceGrpc.newStub(channel);
        initStream();
    }

    private void initStream() {
        if (isInitialized.compareAndSet(false, true)) {
            requestObserver = asyncStub.streamEvent(new StreamObserver<EventResponse>() {
                @Override
                public void onNext(EventResponse response) {
                    System.out.println("gRPC Response: " + response.getStatus());
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("gRPC Error: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("gRPC Stream completed");
                }
            });
        }
    }

    public static synchronized GrpcStreamManager getInstance() {
        if (instance == null) {
            instance = new GrpcStreamManager();
        }
        return instance;
    }

    public void sendEvent(EventRequest request) {
        if (requestObserver != null) {
            requestObserver.onNext(request);
        }
    }
}
