package org.richard.keycloak.spi;

import lombok.*;

/**
 * Created by 7/29/17.
 */
@Value
@Builder
public class KeycloakUserEvent {
    private final String userId;
    private final String username;
    private final String email;
}
