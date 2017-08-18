export const environment = {
  production: true,
    name: 'prod',
  origin: 'http://localhost:8080',
  keycloak: {
   'realm': 'spring-angular',
    'auth-server-url': 'http://localhost:8081/auth',
    'url': 'http://localhost:8081/auth',
    'ssl-required': 'external',
    'resource': 'frontend',
    'clientId': 'frontend',
    'public-client': true
  }
};
