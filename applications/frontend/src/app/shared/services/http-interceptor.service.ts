import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/fromPromise';
import 'rxjs/add/operator/mergeMap';

import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

@Injectable()
export class HttpInterceptorService implements HttpInterceptor {
  // with help from {url https://github.com/auth0/angular2-jwt/blob/v1.0/src/jwt.interceptor.ts }
  // more interceptions at https://angular.io/guide/http
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('Intercepted Request');
    const iToken = this._authService.getToken();
    return Observable.fromPromise(iToken)
      .mergeMap((asyncToken: string) => {
        console.log('Retrieved Token.');
        return this.handle(asyncToken, req, next);
      });
  }

  handle(token: string, req: HttpRequest<any>, next: HttpHandler) {
    const JWT = `Bearer ${token}`;
     const authReq = req.clone({ headers: req.headers.set('Authorization', `Bearer ${token}`) });
   /* const authReq =  req.clone({
      setHeaders: {
        Authorization: JWT
      }
    });*/
    console.log(authReq.headers);
    return next.handle(authReq);
  }

  constructor(private _authService: AuthService) { }

}
