import { Injectable } from '@angular/core';
import { KeycloakService } from './keycloak.service';

@Injectable()
export class AuthService {

  //jwtHelper = new JwtHelper();

  constructor() { }

  isLoggedIn(): Promise<boolean> {
    return this.tokenNotExpired();
  }

  tokenNotExpired(): Promise<boolean> {
    return new Promise<boolean>((resolve, reject) => {
      if (KeycloakService.auth.authz.token) {
        KeycloakService.auth.authz.updateToken(5)
          .success(() => {
            const keycloakToken: string = KeycloakService.auth.authz.token as string;
           /* if (keycloakToken != null && !this.jwtHelper.isTokenExpired(keycloakToken)) {
              resolve(true);
            }
            reject(false);*/
          })
          .error((err) => {
            console.log(err);
            reject(false);
          });
      }
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
