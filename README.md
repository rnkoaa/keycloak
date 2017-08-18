# keycloak
Set of utilities, themes and libraries to enhance keycloak for personal or business use.

## Start angular app with 

```sh
ng serve --environment=local
```

```sh
ng serve -e local
```

```sh
KC_REALM=spring-angular
KC_USERNAME=agyei@domain.com
KC_PASSWORD=agyei
KC_CLIENT=frontend
KC_URL="http://localhost:8081/auth"

# Request Tokens for credentials
KC_RESPONSE=$( \
   curl -k -v \
        -d "username=$KC_USERNAME" \
        -d "password=$KC_PASSWORD" \
        -d 'grant_type=password' \
        -d "client_id=$KC_CLIENT" \
        "$KC_URL/realms/$KC_REALM/protocol/openid-connect/token" \
    | jq .
)

KC_ACCESS_TOKEN=$(echo $KC_RESPONSE| jq -r .access_token)
KC_ID_TOKEN=$(echo $KC_RESPONSE| jq -r .id_token)
KC_REFRESH_TOKEN=$(echo $KC_RESPONSE| jq -r .refresh_token)

$ curl -H "Authorization: Bearer $KC_ACCESS_TOKEN" -v http://localhost:8080/api/me

```