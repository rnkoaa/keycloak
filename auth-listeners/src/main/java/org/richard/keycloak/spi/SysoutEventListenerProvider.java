package org.richard.keycloak.spi;

import com.google.common.base.Strings;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.OperationType;

import java.util.Map;

/**
 * Created by 7/26/17.
 */
public class SysoutEventListenerProvider implements EventListenerProvider {

    SysoutEventListenerProvider() {
    }


    @Override
    public void onEvent(Event event) {
        System.out.println("SysoutEventListenerProvider::OnEvent...");

        // only interested in registration events without errors.
        if (event.getType() == EventType.REGISTER && Strings.isNullOrEmpty(event.getError())) {
            System.out.println("***** Registered Received Event ******");
            System.out.println("EVENT: " + toString(event));
            KeycloakUserEvent.KeycloakUserEventBuilder userEventBuilder = KeycloakUserEvent.builder()
                    .userId(event.getUserId());

            Map<String, String> eventDetails = event.getDetails();
            if (event.getDetails() != null) {
                userEventBuilder.email(eventDetails.getOrDefault("email", ""));
                userEventBuilder.username(eventDetails.getOrDefault("username", ""));
            }

            KeycloakUserEvent keycloakUserEvent = userEventBuilder.build();
            System.out.println(keycloakUserEvent);


            //.username(event.get)
        }
        System.out.println("****************************");
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {
        System.out.println("SysoutEventListenerProvider::onAdminEvent...");
        if (event.getOperationType() == OperationType.CREATE) {
            System.out.println("***** Registered Received Event ******");
            System.out.println("EVENT: " + toString(event));
        }
        System.out.println("****************************");
    }

    private String toString(Event event) {
        StringBuilder sb = new StringBuilder();

        sb.append("type=");
        sb.append(event.getType());
        sb.append(", realmId=");
        sb.append(event.getRealmId());
        sb.append(", clientId=");
        sb.append(event.getClientId());
        sb.append(", userId=");
        sb.append(event.getUserId());
        sb.append(", ipAddress=");
        sb.append(event.getIpAddress());

        if (event.getError() != null) {
            sb.append(", error=");
            sb.append(event.getError());
        }

        if (event.getDetails() != null) {
            for (Map.Entry<String, String> e : event.getDetails().entrySet()) {
                sb.append(", ");
                sb.append(e.getKey());
                if (e.getValue() == null || e.getValue().indexOf(' ') == -1) {
                    sb.append("=");
                    sb.append(e.getValue());
                } else {
                    sb.append("='");
                    sb.append(e.getValue());
                    sb.append("'");
                }
            }
        }

        return sb.toString();
    }

    private String toString(AdminEvent adminEvent) {
        StringBuilder sb = new StringBuilder();

        sb.append("operationType=");
        sb.append(adminEvent.getOperationType());
        sb.append(", realmId=");
        sb.append(adminEvent.getAuthDetails().getRealmId());
        sb.append(", clientId=");
        sb.append(adminEvent.getAuthDetails().getClientId());
        sb.append(", userId=");
        sb.append(adminEvent.getAuthDetails().getUserId());
        sb.append(", ipAddress=");
        sb.append(adminEvent.getAuthDetails().getIpAddress());
        sb.append(", resourcePath=");
        sb.append(adminEvent.getResourcePath());

        if (adminEvent.getError() != null) {
            sb.append(", error=");
            sb.append(adminEvent.getError());
        }

        return sb.toString();
    }

    @Override
    public void close() {
        System.out.println("SysoutEventListenerProvider::close");
    }

}
