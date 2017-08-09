import { Injectable, OnInit } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { KeycloakService } from './keycloak.service';
import { environment } from '../../../environments/environment';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private keycloakService: KeycloakService,
    private router: Router) { }

  canActivate() {
    console.log('[Frontend]: CanActiviate via AuthGuard triggered');

    return new Promise((resolve, reject) => {
      if (KeycloakService.auth.loggedIn) {
        resolve(true);
      } else {
        const authServerUrl = environment.keycloak['auth-server-url'];
        const realm = environment.keycloak['realm'];
        const redirectUrl = `${authServerUrl}/realms/${realm}/protocol/openid-connect/auth?client_id=${environment.keycloak.clientId}&redirect_uri=${environment.origin}&response_mode=fragment&response_type=code&scope=openid`;
        window.location.href = redirectUrl;
        reject(false);
      }
    });
  }

}
