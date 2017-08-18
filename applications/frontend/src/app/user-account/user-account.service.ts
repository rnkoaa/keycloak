import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';

import { UserAccount } from './user-account';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import { KeycloakService } from '../shared/services/keycloak.service';
import { AuthService } from '../shared/services/auth.service';
import { HttpErrorResponse, HttpClient } from '@angular/common/http';

@Injectable()
export class UserAccountService {

  // Define the routes we are going to interact with
  // private myAccountUrl = '/api/me';
  private myAccountUrl = 'http://localhost:8080/api/me';

  constructor(private http: HttpClient,
    private authService: AuthService) { }

  me() {

    //
    /*this.authService.getToken()
      .then(token => {
        console.log(`Keycloak Token: ${token}`);
        const headers = new Headers({
          'Accept': 'application/json',
          'Authorization': `Bearer ${token}`
        });

        const options = new RequestOptions({ headers });
        this.http.get(this.myAccountUrl, options)
          .map(res => res.json())
          .subscribe(myAccount => {
            console.log(myAccount);
          }, (err: HttpErrorResponse) => {
            // console.log(error)
            if (err.error instanceof Error) {
              // A client-side or network error occurred. Handle it accordingly.
              console.log('An error occurred:', err.error.message);
            } else {
              // The backend returned an unsuccessful response code.
              // The response body may contain clues as to what went wrong,
              console.log(`Backend returned code ${err.status}, body was: ${err.error}`);
            }
          });
      });*/

      this.http.get(this.myAccountUrl)
      // .map(res => res)
      .subscribe(myAccount => {
        console.log(myAccount);
      }, (err: HttpErrorResponse) => {
        // console.log(error)
        if (err.error instanceof Error) {
          // A client-side or network error occurred. Handle it accordingly.
          console.log('An error occurred:', err.error.message);
        } else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong,
          console.log(`Backend returned code ${err.status}, body was: ${err.error}`);
        }
      });
  }

}
