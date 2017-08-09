import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';

import { UserAccount } from './user-account';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import { KeycloakService } from '../shared/services/keycloak.service';

@Injectable()
export class UserAccountService {

  // Define the routes we are going to interact with
  // private myAccountUrl = '/api/me';
  private myAccountUrl = 'http://localhost:8081/api/me';

  constructor(private http: Http,
    private keycloak: KeycloakService) { }

  me() {
    this.keycloak.getToken()
      .then(token => {
        // console.log(`Keycloak Token: ${token}`);
        const headers = new Headers({
          'Accept': 'application/json',
          'Authorization': 'Bearer ' + token
        });

        const options = new RequestOptions({ headers });

        this.http.get(this.myAccountUrl, options)
          .map(res => res.json())
          .subscribe(myAccount => {
            console.log(myAccount);
          },

            error => console.log(error));
      })
      .catch(error => {
        console.log('Error retrieving token');
      });
  }

  // Implement a method to handle errors if any
  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
