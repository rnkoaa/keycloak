export const environment = {
  production: false,
  name: 'local',
  origin: 'http://localhost:4200',
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
