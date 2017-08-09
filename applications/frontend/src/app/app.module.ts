import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, Http, XHRBackend, RequestOptions } from '@angular/http';
import { provideAuth } from 'angular2-jwt';

import { AppComponent } from './app.component';

import { routes, appRoutingProviders } from './app.routing';

import { UserAccountService } from './user-account/user-account.service';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { FooterComponent } from './shared/footer/footer.component';
import { LoaderComponent } from './shared/loader/loader.component';
import { HeaderComponent } from './shared/header/header.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';
import { UnauthorizedComponent } from './shared/unauthorized/unauthorized.component';
import { KeycloakService } from './shared/services/keycloak.service';
import { AuthGuardService } from './shared/services/auth-guard.service';
import { DashboardComponent } from './dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    LoaderComponent,
    HeaderComponent,
    SidebarComponent,
    DashboardComponent,
    UnauthorizedComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routes
  ],
  providers: [
    KeycloakService,
    AuthGuardService,
    UserAccountService,
    appRoutingProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
