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

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request).pipe(
      tap(() => {
      }),
      catchError(response => {
        return throwError(response.error ? JSON.parse(response.error) : response);
      })
    )
  }
}

export const ErrorInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: ErrorInterceptor,
  multi: true
};
