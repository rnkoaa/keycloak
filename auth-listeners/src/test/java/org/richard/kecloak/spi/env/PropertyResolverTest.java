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

    public static final String EVENT_LISTENER_APPLICATION_NAME = "event.listener.application.name";
    public static final String TEST_KAFKA_TOPIC = "application.kafka.topic.name";
    public static final String TEST_KAFKA_BROKERS_HOST = "application.kafka.brokers.host";
    private PropertyResolver propertyResolver;

    @Before
    public void setup() {
    }

    @Test
    public void resolveEnvironmentProperty() {
        propertyResolver = new PropertyResolver(new Config.SystemPropertiesScope("event.listener"));
        System.clearProperty("application.name");
        System.out.println(System.getProperty("event.listener.application.name"));
        System.setProperty(EVENT_LISTENER_APPLICATION_NAME, "RegistrationEventPublisher");

        String eventListenerApplicationName = propertyResolver.resolveProperty(EVENT_LISTENER_APPLICATION_NAME);
        assertThat(eventListenerApplicationName).isNotNull().isNotBlank();
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
                .isNotEqualTo("localhost")
                .isEqualTo("kafka");
        System.out.println(eventListenerApplicationName);

    }
}
