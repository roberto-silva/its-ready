import {
  HttpInterceptor,
  HttpRequest,
  HttpResponse,
  HttpErrorResponse,
  HttpHandler,
  HttpEvent, HTTP_INTERCEPTORS
} from '@angular/common/http';

import { tap, catchError } from 'rxjs/operators';
import {Observable, throwError} from "rxjs";
import {Injectable} from "@angular/core";
import {AuthService} from "../app/core/services/auth.service";
import {BASE_API} from "../app/app.constants";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private readonly authService: AuthService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = this.authService.getAccessTokenInStorage();
    let baseUrlLength = BASE_API.length;
    let requestToAPI = request.url.substring(0, baseUrlLength) == BASE_API;
    if(token && requestToAPI && !request.url.includes('/token/refresh')) {
      let authReq = request.clone({headers: request.headers.set('Authorization', 'Bearer ' + token)});
      return next.handle(authReq);
    }
    return next.handle(request);
  }
}

export const AuthInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: AuthInterceptor,
  multi: true
};
