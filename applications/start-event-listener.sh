#!/bin/sh

docker build -t rnkoaa/keycloak-event-listener .

docker run -it \
-e SPRING_PROFILES_ACTIVE=docker \
--network=keycloak_infrastructure \
--name keycloak-event-listeners rnkoaa/keycloak-event-listener
