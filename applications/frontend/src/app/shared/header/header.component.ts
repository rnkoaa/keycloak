import { Component, OnInit } from '@angular/core';
import { KeycloakService } from '../services/keycloak.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private keycloakService: KeycloakService) { }

  ngOnInit() {
  }

  logout(event) {
    event.preventDefault();
    console.log('logout button clicked.');
    this.keycloakService.logout();
  }

}
