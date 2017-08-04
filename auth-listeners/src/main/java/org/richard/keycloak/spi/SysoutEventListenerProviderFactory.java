package org.richard.keycloak.spi;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 7/26/17.
 */
public class SysoutEventListenerProviderFactory implements EventListenerProviderFactory {
    private static final String APPLICATION_KAFKA_BROKERS_HOST = "application.kafka.brokers.host";
    private static final String APPLICATION_KAFKA_BROKERS_PORT = "application.kafka.brokers.port";
    private static final String APPLICATION_KAFKA_TOPIC_NAME = "application.kafka.topic.name";
    private static final String INCLUDE_REALMS = "include.realms";
    private static final String INCLUDE_APPLICATIONS = "include.applications";

    private SysoutEventListenerProvider sysoutEventListenerProvider;

    @Override
    public EventListenerProvider create(KeycloakSession session) {

        return this.sysoutEventListenerProvider;
    }

    @Override
    public void init(Config.Scope config) {
        System.out.println("SysoutEventListenerProviderFactory::init");

        Properties properties = resolveProperties(config);
        String includedRealms;
        String includedApplications;
        if (properties != null)
            properties.list(System.out);

        includedApplications = properties.getProperty(INCLUDE_APPLICATIONS);
        includedRealms = properties.getProperty(INCLUDE_REALMS);

        List<String> realms = Lists.newArrayList();
        List<String> applicationIds = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(includedApplications)) {
            applicationIds = Splitter.on(',').splitToList(includedApplications);
        }

        if (!Strings.isNullOrEmpty(includedRealms)) {
            realms = Splitter.on(',').splitToList(includedRealms);
        }
        CamelContext camelContext = prepareCamelContext(properties);

        //a local cache which is built to determine where a given event will be published
        Map<String, String> topicMap = Maps.newHashMap();
        try {
            camelContext.addRoutes(new KafkaProducerRouteBuilder(topicMap));
            ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
            camelContext.start();
            sysoutEventListenerProvider = new SysoutEventListenerProvider(producerTemplate, applicationIds, realms);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private CamelContext prepareCamelContext(Properties properties) {
        CamelContext camelContext = new DefaultCamelContext();
        //camelContext
        PropertiesComponent propertiesComponent = camelContext.getComponent("properties", PropertiesComponent.class);
        propertiesComponent.setOverrideProperties(properties);

        // setup kafka component with the brokers
        KafkaComponent kafka = new KafkaComponent();
        kafka.setBrokers("{{" + APPLICATION_KAFKA_BROKERS_HOST + "}}:{{" + APPLICATION_KAFKA_BROKERS_PORT + "}}");
        camelContext.addComponent("kafka", kafka);
        return camelContext;
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

        String includedRealms = config.get(INCLUDE_REALMS, classPathProperties.getProperty(INCLUDE_REALMS));
        String includedApplications = config.get(INCLUDE_APPLICATIONS, classPathProperties.getProperty(INCLUDE_APPLICATIONS));
        String kafkaBrokersHosts = config.get(APPLICATION_KAFKA_BROKERS_HOST, classPathProperties.getProperty(APPLICATION_KAFKA_BROKERS_HOST));
        String kafkaBrokersPort = config.get(APPLICATION_KAFKA_BROKERS_PORT, classPathProperties.getProperty(APPLICATION_KAFKA_BROKERS_PORT));
        String applicationTopicName = config.get(APPLICATION_KAFKA_TOPIC_NAME, classPathProperties.getProperty(APPLICATION_KAFKA_TOPIC_NAME));

        Properties properties = new Properties();
        properties.setProperty(APPLICATION_KAFKA_BROKERS_HOST, kafkaBrokersHosts);
        properties.setProperty(APPLICATION_KAFKA_BROKERS_PORT, kafkaBrokersPort);
        properties.setProperty(APPLICATION_KAFKA_TOPIC_NAME, applicationTopicName);
        properties.setProperty(INCLUDE_REALMS, includedRealms);
        properties.setProperty(INCLUDE_APPLICATIONS, includedApplications);
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
