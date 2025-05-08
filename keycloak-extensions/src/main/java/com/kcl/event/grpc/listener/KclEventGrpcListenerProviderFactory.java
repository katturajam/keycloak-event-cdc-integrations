package com.kcl.event.grpc.listener;

import org.keycloak.models.KeycloakSession;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;

public class KclEventGrpcListenerProviderFactory implements EventListenerProviderFactory {

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new KclEventGrpcListenerProvider(session);
    }

    @Override
    public void init(org.keycloak.Config.Scope config) {
        // init logic
    }

    @Override
    public void postInit(org.keycloak.models.KeycloakSessionFactory factory) {
        // post init logic
    }

    @Override
    public void close() {
        // close logic
    }

    @Override
    public String getId() {
        return "kcl-event-grpc-listener";
    }
}
