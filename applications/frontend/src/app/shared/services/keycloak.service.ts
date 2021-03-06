import { Injectable } from '@angular/core';
// import { environment } from '../../../environments/environment';
import * as Keycloak from 'keycloak-js';
import { environment } from 'environments/environment';

@Injectable()
export class KeycloakService {
  static auth: any = {};

  static init(): Promise<any> {
    const keycloakAuth: any = Keycloak(environment.keycloak);
    KeycloakService.auth.loggedIn = false;

    return new Promise((resolve, reject) => {
      keycloakAuth.init({ onLoad: 'check-sso' })
        .success(() => {
          KeycloakService.auth.loggedIn = keycloakAuth.authenticated;
          KeycloakService.auth.authz = keycloakAuth;
          KeycloakService.auth.logoutUrl = keycloakAuth.authServerUrl
            + `/realms/${environment.keycloak.realm}/protocol/openid-connect/logout?redirect_uri=${environment.origin}`;
          resolve();
        })
        .error((err) => {
          console.log('Error Occurred Initializing Keycloak Service.')
          console.log(`${JSON.stringify(err)}`)
          reject(err);
        });
    });
  }


}
