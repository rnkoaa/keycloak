#!/bin/sh

export KAFKA_HOME=/usr/local/Cellar/kafka/0.11.0.0
export REGISTRATION_EVENTS_TOPIC=test-topic

eval `sh ${KAFKA_HOME}/kafka-topics.sh \
--create \
--zookeeper localhost:2181 \
--replication-factor 1 \
--partitions 1 \
--topic ${REGISTRATION_EVENTS_TOPIC}`