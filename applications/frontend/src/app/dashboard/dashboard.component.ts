import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import * as Keycloak from 'keycloak-js';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { environment } from 'environments/environment';
import { KeycloakService } from 'app/shared/services/keycloak.service';

declare var jQuery: any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private _keycloakServe: KeycloakService) { }

  ngOnInit() {
    this._keycloakServe.getToken().then(token => {
      console.log(`keycloak token: ${token}`);
    }).catch(err => {
      console.log('Error retrieving token');
    })
  }

}
