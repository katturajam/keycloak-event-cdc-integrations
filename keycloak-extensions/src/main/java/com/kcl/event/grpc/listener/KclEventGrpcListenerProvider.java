package com.kcl.event.grpc.listener;

import com.kcl.event.grpc.*;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;

import com.google.gson.Gson;

public class KclEventGrpcListenerProvider implements EventListenerProvider {
    private final GrpcStreamManager streamManager = GrpcStreamManager.getInstance();
    private final Gson gson = new Gson();
    private final KeycloakSession session;

    public KclEventGrpcListenerProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void onEvent(Event event) {
        EventRequest request = EventRequest.newBuilder()
                .setEventType("USER_EVENT")
                .setRealmId(event.getRealmId())
                .setClientId(event.getClientId() != null ? event.getClientId() : "")
                .setUserId(event.getUserId() != null ? event.getUserId() : "")
                .setIpAddress(event.getIpAddress() != null ? event.getIpAddress() : "")
                .setPayloadJson(gson.toJson(event))
                .build();

        streamManager.sendEvent(request);
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean includeRepresentation) {
        EventRequest request = EventRequest.newBuilder()
                .setEventType("ADMIN_EVENT")
                .setRealmId(adminEvent.getRealmId())
                .setClientId(adminEvent.getAuthDetails().getClientId() != null ? adminEvent.getAuthDetails().getClientId() : "")
                .setUserId(adminEvent.getAuthDetails().getUserId() != null ? adminEvent.getAuthDetails().getUserId() : "")
                .setIpAddress(adminEvent.getAuthDetails().getIpAddress() != null ? adminEvent.getAuthDetails().getIpAddress() : "")
                .setPayloadJson(gson.toJson(adminEvent))
                .build();

        streamManager.sendEvent(request);
    }

    @Override
    public void close() {
        // Cleanup if needed
    }
}
