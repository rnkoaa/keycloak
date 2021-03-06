package org.amoako.agyei.keycloak.registration.provider;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import org.apache.camel.ProducerTemplate;
import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.OperationType;

import java.util.List;
import java.util.Map;

/**
 * Created by 7/26/17.
 */
public class RegistrationEventListenerProvider implements EventListenerProvider {

    private static final Logger LOG = Logger.getLogger(RegistrationEventListenerProvider.class);

    private final ProducerTemplate producerTemplate;
    private final List<String> clientIds;
    private final List<String> realms;


    RegistrationEventListenerProvider(ProducerTemplate producerTemplate, List<String> clientIds, List<String> realms) {
        this.producerTemplate = producerTemplate;
        this.clientIds = clientIds;
        this.realms = realms;
    }


    @Override
    public void onEvent(Event event) {
        LOG.info("RegistrationEventListenerProvider::OnEvent...");
        System.out.println("Realms: " + Joiner.on(",").join(realms));
        System.out.println("Client Ids: " + Joiner.on(",").join(clientIds));
        /*
            Process the event to emit if there was no error and the event was a registration
         */
        if (Strings.isNullOrEmpty(event.getError()) && event.getType() == EventType.REGISTER) {
            LOG.info("***** Registered Received Event ******");
            LOG.info("EVENT: " + toString(event));

            /**
             * Event should come from an client we are interested in
             * within a particular realm.
             * However, if no realm or client id is available, the event
             * will be processed anyway since this will presumably be interested by all
             * topics.
             */
            boolean shouldProcess = isEventInteresting(event);
            System.out.println("Should Process: " + shouldProcess);

            if (shouldProcess) {
                KeycloakUserEvent.KeycloakUserEventBuilder userEventBuilder = KeycloakUserEvent.builder()
                        .userId(event.getUserId())
                        .time(event.getTime());

                Map<String, String> eventDetails = event.getDetails();
                if (event.getDetails() != null) {
                    userEventBuilder.email(eventDetails.getOrDefault("email", ""));
                    userEventBuilder.username(eventDetails.getOrDefault("username", ""));
                }

                KeycloakUserEvent keycloakUserEvent = userEventBuilder

                        .build();
                producerTemplate.sendBody("kafka:{{application.kafka.topic.name}}", keycloakUserEvent.toString());
            }
        }
        LOG.info("****************************");
    }

    private boolean isEventInteresting(Event event) {
        String realmId = event.getRealmId();
        String clientId = event.getClientId();
        System.out.println("Event Realm Id: " + realmId);
        System.out.println("Event Client Id: " + clientId);

        if (realms.size() > 0) {
            if (!realms.contains(realmId))
                return false;
        }

        if (clientIds.size() > 0) {
            if (!clientIds.contains(clientId))
                return false;
        }
        return true;
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {
        LOG.info("RegistrationEventListenerProvider::onAdminEvent...");
        if (event.getOperationType() == OperationType.CREATE) {
            LOG.info("***** Registered Received Event ******");
            LOG.info("EVENT: " + toString(event));
        }
        LOG.info("****************************");
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
        LOG.info("RegistrationEventListenerProvider::close");
    }

}
