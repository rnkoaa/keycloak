import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
// import { KeycloakService } from './app/shared/services/keycloak.service';
import * as Keycloak from 'keycloak-js';
import { KeycloakService } from './app/shared/services/keycloak.service';

if (environment.production) {
  enableProdMode();
}

/*
KeycloakService.init()
  .then(() => {
    const platform = platformBrowserDynamic();
    platform.bootstrapModule(AppModule);
  })
  .catch(() => window.location.reload());
*/
 const platform = platformBrowserDynamic();
 platform.bootstrapModule(AppModule);
