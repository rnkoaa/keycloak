package org.richard.keycloak.spi;

import com.google.common.collect.Sets;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.OperationType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * Created by 7/29/17.
 */
@Configuration
public class EventListenerConfiguration {

    @Bean
    public SysoutEventListenerProvider sysoutEventListenerProvider(){
         return new SysoutEventListenerProvider();
    }
}
