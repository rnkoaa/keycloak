package org.richard.keycloak.spi;

import org.apache.camel.builder.RouteBuilder;

import java.util.Map;

/**
 * Created by 8/2/17.
 */
public class KafkaProducerRouteBuilder extends RouteBuilder {

    private final Map<String, String> topicMap;

    KafkaProducerRouteBuilder(Map<String, String> topicMap){
        this.topicMap = topicMap;
    }
    @Override
    public void configure() throws Exception {
            /*from("direct:kafkaRoute")
                    .to("kafka:{{kafka.host}}:{{kafka.port}}?" +
                            "topic=javaKafkaBasicTopicName&" +
                            "groupId=javaKafkaBasicGroupId&" +
                            "autoOffsetReset=earliest&" +
                            "consumersCount=1");*/

            /*from("direct:kafkaRoute")
                    .to("kafka:javaKafkaBasicTopicName");*/

        from("direct:kafkaRoute")
                .to("kafka:{{application.kafka.topic.name}}");
    }
}
