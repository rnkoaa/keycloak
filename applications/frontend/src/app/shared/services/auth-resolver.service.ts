import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Injectable } from "@angular/core";
import { KeycloakService } from './keycloak.service';

@Injectable()
export class AuthResolverService implements Resolve<Boolean> {
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Boolean | Observable<Boolean> | Promise<Boolean> {
    // if (!KeycloakService.auth.loggedIn) {
    //   window.location.href = 'http://localhost:8080/auth/realms/master/protocol/' +
    //     'openid-connect/auth?client_id=spring-angular-frontend&redirect_uri=http://localhost:4200' +
    //     '&response_mode=fragment&response_type=code&scope=openid';
    //     window.location.reload();
    //   return false;
    // }
    // return true;
    return true;
  }

}
