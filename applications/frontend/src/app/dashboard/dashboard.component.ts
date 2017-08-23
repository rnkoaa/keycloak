import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import * as Keycloak from 'keycloak-js';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { environment } from 'environments/environment';
import { KeycloakService } from '../shared/services/keycloak.service';
import { AuthService } from '../shared/services/auth.service';

declare var jQuery: any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private _authService: AuthService) { }

  ngOnInit() {
    /* this._authService
      .isLoggedIn()
      .then(loggedIn => {
        console.log(`Is User Logged In: ${loggedIn}`)
      })
      .catch(err => {
        console.log(`Error isLoggedIn: ${err}`)
      });*/
  }

}
