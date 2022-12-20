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

        switch (response.error.status) {
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

  handleForbiddenError(req: any, next: HttpHandler) {
    if (!!this.authService.getToken()) {
      let cloneReq = req.clone();
      next.handle(req);

      this.loginService.refreshToken();
      next.handle(cloneReq);
    } else {
      this.loginService.logout();
    }
  }

// TODO I must improve refresh token strategy, currently it is just replacing tokens
  private handleUnauthorizedError(req: any, next: HttpHandler) {

    if (!!this.authService.getToken()) {
      return this.loginService.refreshToken();
      return next.handle(req);
    }
  }
}

export const ErrorInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: ErrorInterceptor,
  multi: true
};
