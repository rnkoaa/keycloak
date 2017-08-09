package com.richard.agyei.kafka.consumer.kafkaconsumer;

import org.apache.camel.CamelContext;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KeycloakEventListenerApplication {

	@Bean
	public CamelContextConfiguration camelContextConfiguration() {
		return new CamelContextConfiguration() {
			@Override
			public void beforeApplicationStart(CamelContext camelContext) {
				// setup kafka component with the brokers
				KafkaComponent kafka = new KafkaComponent();
				kafka.setBrokers("{{kafka.brokers.host}}:{{kafka.brokers.port}}");
				kafka.getConfiguration().setAutoOffsetReset("earliest");
				kafka.getConfiguration().setGroupId("test-group-1");
				kafka.getConfiguration().setConsumersCount(1);
				camelContext.addComponent("kafka", kafka);
			}

			@Override
			public void afterApplicationStart(CamelContext camelContext) {

			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(KeycloakEventListenerApplication.class, args);
	}
}
