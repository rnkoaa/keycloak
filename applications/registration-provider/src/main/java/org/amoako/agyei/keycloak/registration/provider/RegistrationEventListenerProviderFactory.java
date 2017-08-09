package org.amoako.agyei.keycloak.registration.provider;

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
import org.amoako.agyei.keycloak.registration.provider.env.PropertyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 7/26/17.
 */
public class RegistrationEventListenerProviderFactory implements EventListenerProviderFactory {
    private static final String APPLICATION_ID = "registration-provider";

    private static final Logger logger = LoggerFactory.getLogger(RegistrationEventListenerProvider.class);

    private static final String APPLICATION_KAFKA_BROKERS_HOST = "application.kafka.brokers.host";
    private static final String APPLICATION_KAFKA_BROKERS_PORT = "application.kafka.brokers.port";
    private static final String APPLICATION_KAFKA_TOPIC_NAME = "application.kafka.topic.name";
    private static final String INCLUDE_REALMS = "include.realms";
    private static final String INCLUDE_CLIENTS = "include.clients";
    private static List<String> APPLICATION_PROPERTIES = new ArrayList<String>() {
        {
            add(APPLICATION_KAFKA_BROKERS_HOST);
            add(APPLICATION_KAFKA_BROKERS_PORT);
            add(APPLICATION_KAFKA_TOPIC_NAME);
            add(INCLUDE_REALMS);
            add(INCLUDE_CLIENTS);
        }
    };

    private RegistrationEventListenerProvider sysoutEventListenerProvider;

    @Override
    public EventListenerProvider create(KeycloakSession session) {

        return this.sysoutEventListenerProvider;
    }

    @Override
    public void init(Config.Scope config) {
        logger.info("RegistrationEventListenerProviderFactory::init");
        Properties properties = resolveProperties(config);
        String includedRealms;
        String includedApplications;

        // dump the properties for debug purposes, should guard the call
        properties.list(System.out);

        includedApplications = properties.getProperty(INCLUDE_CLIENTS);
        includedRealms = properties.getProperty(INCLUDE_REALMS);

        List<String> realms = Lists.newArrayList();
        List<String> clientIds = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(includedApplications)) {
            clientIds = Splitter.on(',').splitToList(includedApplications);
        }

        // clientIds.forEach(clientId -> logger.info("Client Id From Property: " + clientId));

        if (!Strings.isNullOrEmpty(includedRealms)) {
            realms = Splitter.on(',').splitToList(includedRealms);
        }

        // realms.forEach(realm -> logger.info("Realm From Property: " + realm));
        CamelContext camelContext = prepareCamelContext(properties);

        //a local cache which is built to determine where a given event will be published
        Map<String, String> topicMap = Maps.newHashMap();
        try {
            // camelContext.addRoutes(new KafkaProducerRouteBuilder(topicMap));
            ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
            camelContext.start();
            sysoutEventListenerProvider = new RegistrationEventListenerProvider(producerTemplate, clientIds, realms);
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
        PropertyResolver propertyResolver = new PropertyResolver(new String[]{"application.properties"}, config);
        Map<String, String> resolvedProperties = propertyResolver.resolveProperties(APPLICATION_PROPERTIES);
        Properties properties = new Properties();
        properties.putAll(resolvedProperties);

        return properties;
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        logger.info("RegistrationEventListenerProviderFactory::postInit");
    }

    @Override
    public void close() {
        logger.info("RegistrationEventListenerProviderFactory::close");
    }

    @Override
    public String getId() {
        return APPLICATION_ID;
    }
}
