import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { UnauthorizedComponent } from './shared/unauthorized/unauthorized.component';
import { AuthGuardService } from './shared/services/auth-guard.service';
import { DashboardComponent } from './dashboard/dashboard.component';

export const router: Routes = [
  //  { path: "",redirectTo: 'home', pathMatch: 'full' },
  //   { path: "**", component: PageNotFoundComponent },
  //  { path: 'pokemon/:id', component: PokemonInfoComponent },
  // { path: '', component: PokemonListComponent }
  {
    path: '',
    canActivate: [AuthGuardService],
    component: DashboardComponent
  },
  { path: 'unauthorized', component: UnauthorizedComponent }
];

export const appRoutingProviders: any[] = [
];

export const routes: ModuleWithProviders = RouterModule.forRoot(router);
