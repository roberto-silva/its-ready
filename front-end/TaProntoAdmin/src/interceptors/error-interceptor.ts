import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';

import {catchError} from 'rxjs/operators';
import {Observable, switchMap, throwError} from "rxjs";
import {Injectable} from "@angular/core";
import {LoginService} from "../app/models/login/login.service";
import {AuthService} from "../app/core/services/auth.service";
import {ToastrService} from "ngx-toastr";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(
    private readonly loginService: LoginService,
    private readonly authService: AuthService,
    private readonly toastrService: ToastrService
  ) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError((error: any) => {
      console.log(error);
      if (!this.authService.refreshTokenExpired() && error.status === 403 && this.authService.getRefreshTokenInStorage()) {
        return this.refreshToken(request, next);
      }

      if (this.authService.refreshTokenExpired()) {
        this.loginService.logout();
        this.toastrService.info("User disconnected due to inactivity, to continue using the application, log in again by entering the login and password.");
      }

      if (error.status === 401) {
        this.loginService.logout();
        this.toastrService.warning("Unauthenticated user, to continue using the app, login again by entering login and password.");
      }
      return throwError(error);
    }));
  }

  private refreshToken(request: HttpRequest<any>, next: HttpHandler) {
    return this.authService.getRefreshToken().pipe(switchMap((response) => {
      this.loginService.successfulLogin(response.body);
      return next.handle(request.clone({headers: request.headers.set('Authorization', 'Bearer ' + this.authService.getAccessTokenInStorage())}));
    }), catchError((error) => {
      this.loginService.logout();
      return throwError(error);
    }));
  }

}

export const ErrorInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: ErrorInterceptor,
  multi: true
};
