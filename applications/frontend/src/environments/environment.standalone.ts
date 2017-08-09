export const environment = {
  production: true,
  name: 'standalone',
  origin: '',
  keycloak: {
    'realm': 'master',
    'auth-server-url': 'http://localhost:8080/auth',
    'ssl-required': 'external',
    'resource': 'spring-angular-frontend',
    'public-client': true,
    'use-resource-role-mappings': true
  }
};
