package com.richard.agyei.kafka.consumer.kafkaconsumer;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.springframework.stereotype.Component;

@Component
public class KeycloakEventListener extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:{{application.kafka.topic.name}}")
                .log("${body}");
    }
}
