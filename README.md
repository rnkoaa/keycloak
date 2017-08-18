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
KC_REALM=spring-security-adapter-test
KC_USERNAME=tester
KC_PASSWORD=test
KC_CLIENT=dummy
KC_CLIENT_SECRET=98732edc-573e-4a45-8fbc-0feee1ebcccb
KC_URL="http://localhost:8180/auth"

# Request Tokens for credentials
KC_RESPONSE=$( \
   curl -k -v \
        -d "username=$KC_USERNAME" \
        -d "password=$KC_PASSWORD" \
        -d 'grant_type=password' \
        -d "client_id=$KC_CLIENT" \
        -d "client_secret=$KC_CLIENT_SECRET" \
        "$KC_URL/realms/$KC_REALM/protocol/openid-connect/token" \
    | jq .
)

KC_ACCESS_TOKEN=$(echo $KC_RESPONSE| jq -r .access_token)
KC_ID_TOKEN=$(echo $KC_RESPONSE| jq -r .id_token)
KC_REFRESH_TOKEN=$(echo $KC_RESPONSE| jq -r .refresh_token)
```