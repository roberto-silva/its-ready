import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';

import {catchError, tap} from 'rxjs/operators';
import {Observable, throwError} from "rxjs";
import {Injectable} from "@angular/core";
import {LoginService} from "../app/models/login/login.service";
import {AuthService} from "../app/core/services/auth.service";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(
    private readonly loginService: LoginService,
    private readonly authService: AuthService
  ) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request).pipe(
      tap(() => {
      }),
      catchError(response => {
        const result = response.error.status || response.status;
        switch (result) {
          case 401:
            this.handleUnauthorizedError(request, next);
            break;
          case 403:
            this.handleForbiddenError(request, next);
            break;
          default:
            break;
        }

        return throwError(response.error);
      })
    )
  }

  handleForbiddenError(request: any, next: HttpHandler): any {
    if (!!this.authService.getAccessTokenInStorage() && !this.authService.refreshTokenExpired()) {
      return this.loginService.refreshToken(request, next);
    } else {
      this.loginService.logout();
    }
  }

  private handleUnauthorizedError(request: any, next: HttpHandler): any {
    if (!!this.authService.getAccessTokenInStorage() && !this.authService.refreshTokenExpired()) {
      return this.loginService.refreshToken(request, next);
    } else {
      this.loginService.logout();
    }
  }
}

export const ErrorInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: ErrorInterceptor,
  multi: true
};
