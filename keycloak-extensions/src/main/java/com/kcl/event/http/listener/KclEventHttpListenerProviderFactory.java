package com.kcl.event.http.listener;

import org.keycloak.models.KeycloakSession;

import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;

public class KclEventHttpListenerProviderFactory implements EventListenerProviderFactory {

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new KclEventHttpListenerProvider(session);
    }

    @Override
    public void init(org.keycloak.Config.Scope config) {
        // init logic if needed
    }

    @Override
    public void postInit(org.keycloak.models.KeycloakSessionFactory factory) {
        // post init logic if needed
    }

    @Override
    public void close() {
        // cleanup logic if needed
    }

    @Override
    public String getId() {
        return "kcl-event-http-listener";
    }
}
