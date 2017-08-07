#!/bin/sh

export KAFKA_HOME=/usr/local/Cellar/kafka/0.11.0.0
export REGISTRATION_EVENTS_TOPIC=test-topic
export CONSUMER_GROUP_ID=test.consumer.group.id.1

`sh ${KAFKA_HOME}/bin/kafka-console-consumer \
--bootstrap-server localhost:9092 \
--topic ${REGISTRATION_EVENTS_TOPIC} \
--consumer-property group.id=${CONSUMER_GROUP_ID}`