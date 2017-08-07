package org.richard.kecloak.spi.env;

import org.junit.Before;
import org.junit.Test;
import org.keycloak.Config;
import org.richard.keycloak.spi.env.PropertyResolver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by 8/6/17.
 */
public class PropertyResolverTest {

    private static final String EVENT_LISTENER_APPLICATION_NAME = "application.name";
    private static final String TEST_KAFKA_TOPIC = "application.kafka.topic.name";
    private static final String TEST_KAFKA_BROKERS_HOST = "application.kafka.brokers.host";
    private PropertyResolver propertyResolver;

    @Before
    public void setup() {
    }

    @Test
    public void resolveEnvironmentProperty() {
        String environmentKey = EVENT_LISTENER_APPLICATION_NAME.replace('.', '_').toUpperCase();
        System.clearProperty(environmentKey);
        System.setProperty(environmentKey, "RegistrationEvents");
        propertyResolver = new PropertyResolver(new Config.SystemPropertiesScope("event.listener"));

        String eventListenerApplicationName = propertyResolver.resolveProperty(EVENT_LISTENER_APPLICATION_NAME);
        assertThat(eventListenerApplicationName).isNotNull().isNotBlank().isEqualTo("RegistrationEvents");
        System.out.println(eventListenerApplicationName);

    }

    @Test
    public void resolveEnvironmentFromClassPathProperty() {
        System.clearProperty(TEST_KAFKA_TOPIC);
        propertyResolver = new PropertyResolver(new String[]{"test-application.properties"},
                new Config.SystemPropertiesScope("event.listener"));

        String eventListenerApplicationName = propertyResolver.resolveProperty(TEST_KAFKA_TOPIC);
        assertThat(eventListenerApplicationName).isNotNull().isNotBlank()
                .isEqualTo("test.registration.events");
        System.out.println(eventListenerApplicationName);
    }

    @Test
    public void resolveShouldObeyHierachy() {
        System.clearProperty(TEST_KAFKA_BROKERS_HOST);
        System.setProperty(TEST_KAFKA_BROKERS_HOST, "kafka");
        propertyResolver = new PropertyResolver(new String[]{"test-application.properties"},
                new Config.SystemPropertiesScope("event.listener"));

        String eventListenerApplicationName = propertyResolver.resolveProperty(TEST_KAFKA_BROKERS_HOST);
        assertThat(eventListenerApplicationName).isNotNull().isNotBlank()
                .isEqualTo("localhost")
                .isNotEqualTo("kafka");
        System.out.println(eventListenerApplicationName);

    }
}
