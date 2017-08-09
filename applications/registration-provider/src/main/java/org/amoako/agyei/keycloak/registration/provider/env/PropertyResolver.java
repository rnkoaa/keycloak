package org.amoako.agyei.keycloak.registration.provider.env;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import org.keycloak.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by 8/6/17.
 */
public class PropertyResolver {
    private final Config.Scope config;
    private Properties classPathProperties;
    private static final Logger logger = LoggerFactory.getLogger(PropertyResolver.class);

    public PropertyResolver(Config.Scope config) {
        classPathProperties = new Properties();
        this.config = config;
        try (final InputStream stream =
                     this.getClass()
                             .getClassLoader()
                             .getResourceAsStream("application.properties")) {
            classPathProperties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
            classPathProperties = new Properties();
        }
    }

    public PropertyResolver(String[] propertyFileLocations, Config.Scope config) {
        classPathProperties = new Properties();
        this.config = config;
        Stream.of(propertyFileLocations)
                .forEach(propertyFileLocation -> {
                    try (final InputStream stream =
                                 this.getClass()
                                         .getClassLoader()
                                         .getResourceAsStream(propertyFileLocation)) {
                        Properties properties = new Properties();
                        properties.load(stream);
                        classPathProperties.putAll(properties);
                        //  classPathProperties.load(stream);
                    } catch (IOException e) {
                        e.printStackTrace();
                        classPathProperties = new Properties();
                    }
                });

    }

    public String resolveProperty(String propertyName) {
        String envProperty = propertyName;
        envProperty = updateProperty(envProperty);
        String property = System.getenv(envProperty);

        logger.info("Resolved Property: " + envProperty + " Environment: " + property);
        Map<String, String> systemEnv = System.getenv();
        final Joiner.MapJoiner mapJoiner = Joiner.on('&').withKeyValueSeparator("=");

        String join = mapJoiner.join(systemEnv);
        logger.info("*********************************");
        logger.info(join);
        logger.info("*********************************");
        if (Strings.isNullOrEmpty(property)) {
            //resolve from the properties file
            property = config.get(propertyName);
            if (Strings.isNullOrEmpty(property))
                property = classPathProperties.getProperty(propertyName, "");
        }
        return property;
    }

    private String updateProperty(String envProperty) {
        return envProperty.replace(".", "_").toUpperCase();
    }

    public Map<String, String> resolveProperties(String[] propertyNames) {
        List<String> properties = Arrays.asList(propertyNames);
        return resolveProperties(properties);
    }

    public Map<String, String> resolveProperties(List<String> propertyNames) {
        Map<String, String> resolvedProperties = new HashMap<>(propertyNames.size());
        propertyNames.forEach(propertyName -> {
            resolvedProperties.put(propertyName, resolveProperty(propertyName));
        });

        return resolvedProperties;
    }
}
