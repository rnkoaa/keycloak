
## Create an admin account on master realm using shell command

To Add a user via docker command
```sh
docker exec keycloak /opt/jboss/keycloak/bin/add-user-keycloak.sh -r master -u admin -p admin
docker restart keycloak
```

```sh
./add-user-keycloak.sh -r master -u admin -p admin
```

http://www.codingpedia.org/ama/how-to-configure-nginx-in-production-to-serve-angular-app-and-reverse-proxy-nodejs#nginx-configuration-to-reverse-proxy-keycloak

https://medium.com/@auscunningham/create-a-custom-theme-for-keycloak-8781207be604

https://github.com/keycloak/keycloak/blob/master/examples/themes/src/main/resources/theme/address/account/account.ftl
