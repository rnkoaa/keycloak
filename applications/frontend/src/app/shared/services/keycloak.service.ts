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
          console.log(`Keycloak Auth: ${keycloakAuth.authenticated}`);
          KeycloakService.auth.loggedIn = keycloakAuth.authenticated;
          KeycloakService.auth.authz = keycloakAuth;
          KeycloakService.auth.logoutUrl = keycloakAuth.authServerUrl
            + `/realms/${environment.keycloak.realm}/protocol/openid-connect/logout?redirect_uri=${environment.origin}`;
          resolve();
        })
        .error((err) => {
          console.log('Error Occurred Initializing Keycloak Service.')
          console.log(`${JSON.stringify(err)}`)
          reject();
        });
    });
  }

  logout() {
    console.log('*** LOGOUT');
    KeycloakService.auth.loggedIn = false;
    KeycloakService.auth.authz = null;

    window.location.href = KeycloakService.auth.logoutUrl;
  }

  getToken(): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      if (KeycloakService.auth.authz.token) {
        KeycloakService.auth.authz.updateToken(5)
          .success(() => {
            resolve(<string>KeycloakService.auth.authz.token);
          })
          .error(() => {
            reject('Failed to refresh token');
          });
      }
    });
  }
}
