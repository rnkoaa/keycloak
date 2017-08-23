import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, Http, XHRBackend, RequestOptions } from '@angular/http';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { JwtModule, JWT_OPTIONS } from '@auth0/angular-jwt';
import { routes, appRoutingProviders } from './app.routing';

import { UserAccountService } from './user-account/user-account.service';
import { FooterComponent } from './shared/footer/footer.component';
import { LoaderComponent } from './shared/loader/loader.component';
import { HeaderComponent } from './shared/header/header.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';
import { UnauthorizedComponent } from './shared/unauthorized/unauthorized.component';
import { KeycloakService } from './shared/services/keycloak.service';
import { AuthGuardService } from './shared/services/auth-guard.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthService } from './shared/services/auth.service';
import { HttpInterceptorService } from './shared/services/http-interceptor.service';

/*export function jwtOptionsFactory(authService) {
  return {
    tokenGetter: () => {
      console.log('token getter called to get token');
      return authService.getToken();
    }
  }
};*/

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    LoaderComponent,
    HeaderComponent,
    SidebarComponent,
    DashboardComponent,
    UnauthorizedComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    HttpModule,
    routes
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true,
    },
    KeycloakService,
    AuthService,
    AuthGuardService,
    UserAccountService,
    appRoutingProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
