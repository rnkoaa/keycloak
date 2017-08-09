// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  name: 'base',
  origin: 'http://localhost:4200',
  keycloak: {
    'realm': 'master',
    'auth-server-url': 'http://localhost:8080/auth',
    'url': 'http://localhost:8080/auth',
    'ssl-required': 'external',
    'logout-url': '',
    'resource': 'spring-angular-frontend',
    'clientId': 'spring-angular-frontend',
    'public-client': true,
    'use-resource-role-mappings': true
  }
};
