# Keycloak EventListener Samples

## How to Install the EventListener
1. Create a jar file from the commandline
    ```sh
    $ ./gradlew jar
    ```
    
2. Once the jar has been created, we can copy the jar into the deployment folder of the keycloak installation.
    ```sh
    $ cp ./build/libs/auth-listeners.jar $KEYCLOAK_HOME/standalone/deployments
    ```
    
3. Restart the keycloak for the listener to be deployed and picked up.

## How to Install dependency as a module in Keycloak
On Windows

```cmd
jboss-cli.bat --command="module add --name=Mymodule1 --resources=../providers/xxClient.jar" 
```

On *nix

```sh
jboss-cli.sh --command="module add --name=Mymodule1 --resources=../providers/xxClient.jar" 
```