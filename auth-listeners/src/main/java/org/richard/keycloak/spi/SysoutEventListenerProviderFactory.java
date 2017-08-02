package org.richard.keycloak.spi;

import com.google.common.base.Strings;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 7/26/17.
 */
public class SysoutEventListenerProviderFactory implements EventListenerProviderFactory {
    private static final String APPLICATION_KAFKA_BROKERS_HOST = "application.kafka.brokers.host";
    private static final String APPLICATION_KAFKA_BROKERS_PORT = "application.kafka.brokers.port";
    private static final String APPLICATION_KAFKA_TOPIC_NAME = "application.kafka.topic.name";

    private SysoutEventListenerProvider sysoutEventListenerProvider;

    @Override
    public EventListenerProvider create(KeycloakSession session) {

        return this.sysoutEventListenerProvider;
    }

    @Override
    public void init(Config.Scope config) {
        //Properties classpath = new Properties("");
        System.out.println("SysoutEventListenerProviderFactory::init");

        Properties properties = resolveProperties(config);
        if (properties != null)
            properties.list(System.out);

        CamelContext camelContext = new DefaultCamelContext();
        //camelContext
        PropertiesComponent propertiesComponent = camelContext.getComponent("properties", PropertiesComponent.class);
        propertiesComponent.setOverrideProperties(properties);

        // setup kafka component with the brokers
        KafkaComponent kafka = new KafkaComponent();
        kafka.setBrokers("{{" + APPLICATION_KAFKA_BROKERS_HOST + "}}:{{" + APPLICATION_KAFKA_BROKERS_PORT + "}}");
        camelContext.addComponent("kafka", kafka);

        try {
            camelContext.addRoutes(new KafkaProducerRouteBuilder());
            ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
            camelContext.start();
            sysoutEventListenerProvider = new SysoutEventListenerProvider(producerTemplate);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Properties resolveProperties(Config.Scope config) {

        //load default application properties. this is fine in local
        // development but must be overridden in the standalone.xml or
        // standalone-ha.xml or standalone-cluster.xml for it to be production ready
        Properties classPathProperties = new Properties();
        try (final InputStream stream =
                     this.getClass()
                             .getClassLoader()
                             .getResourceAsStream("application.properties")) {
            classPathProperties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String kafkaBrokersHosts = config.get(APPLICATION_KAFKA_BROKERS_HOST, classPathProperties.getProperty(APPLICATION_KAFKA_BROKERS_HOST));
        String kafkaBrokersPort = config.get(APPLICATION_KAFKA_BROKERS_PORT, classPathProperties.getProperty(APPLICATION_KAFKA_BROKERS_PORT));
        String applicationTopicName = config.get(APPLICATION_KAFKA_TOPIC_NAME, classPathProperties.getProperty(APPLICATION_KAFKA_TOPIC_NAME));

        Properties properties = new Properties();
        properties.setProperty(APPLICATION_KAFKA_BROKERS_HOST, kafkaBrokersHosts);
        properties.setProperty(APPLICATION_KAFKA_BROKERS_PORT, kafkaBrokersPort);
        properties.setProperty(APPLICATION_KAFKA_TOPIC_NAME, applicationTopicName);
        return properties;
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        System.out.println("SysoutEventListenerProviderFactory::postInit");
    }

    @Override
    public void close() {
        System.out.println("SysoutEventListenerProviderFactory::close");
    }

    @Override
    public String getId() {
        return "sysout";
    }
}
