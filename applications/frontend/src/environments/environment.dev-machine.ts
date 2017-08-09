export const environment = {
  production: false,
  name: 'dev-machine',
  origin: 'http://localhost:4200',
  keycloak: {
    'realm': 'spring-angular',
    'auth-server-url': 'http://192.168.99.100:8080/auth',
    'url': 'http://192.168.99.100:8080/auth',
    'ssl-required': 'external',
    'resource': 'spring-angular-frontend',
    'clientId': 'spring-angular-frontend',
    'public-client': true,
    'use-resource-role-mappings': true
  }
};
