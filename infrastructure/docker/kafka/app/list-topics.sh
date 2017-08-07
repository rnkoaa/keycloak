#!/bin/sh

export KAFKA_HOME=/usr/local/Cellar/kafka/0.11.0.0
eval `${KAFKA_HOME}/bin/kafka-topics \
--list \
--zookeeper localhost:2181`