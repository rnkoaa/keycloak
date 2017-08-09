import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { UserAccountService } from './user-account/user-account.service';

import { environment } from '../environments/environment';

declare var jQuery: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {
  title = 'app works!';
  products: string[] = [];

  constructor(
    private userAccountService: UserAccountService,
    private http: Http
  ) { }

  ngAfterViewInit() {
    jQuery('#sidebar-menu, #customize-menu').metisMenu({ activeClass: 'active open' });
  }

  ngOnInit() {
    this.userAccountService.me();
    console.log(`OnInit Initiated.`);
    console.log(environment.name);
  }

  logout() {
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json().error || 'Server error');
  }

  goToLogin() {
    //window.location.href = 'http://localhost:8080/login?redirect=http://localhost:4200/';
  }
}
