package com.kcl.event.http.listener;

import com.kcl.event.http.HttpEventPublisher;
import com.google.gson.Gson;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;

public class KclEventHttpListenerProvider implements EventListenerProvider {
    private final Gson gson = new Gson();
    private final KeycloakSession session;

    public KclEventHttpListenerProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void onEvent(Event event) {
        HttpEventPublisher.publish(event);
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean includeRepresentation) {
        HttpEventPublisher.publish(adminEvent);
    }

    @Override
    public void close() {
        // No-op
    }
}
