import {Injectable} from '@angular/core';
import {AuthService, UserCredentials} from "../../core/services/auth.service";
import {HttpHandler, HttpResponse} from "@angular/common/http";
import {ToastrService} from "ngx-toastr";
import {ACCESS_TOKEN, BASE_API, REFRESH_TOKEN} from "../../app.constants";
import {Router} from "@angular/router";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private readonly authService: AuthService,
    private readonly toastrService: ToastrService,
    private readonly router: Router
  ) {
  }

  login(userCredentials: UserCredentials) {
    this.authService.getAccessToken(userCredentials).subscribe((value: HttpResponse<any>) => {
      this.successfulLogin(value.body);
      this.toastrService.success("Login successful.");
      this.router.navigate(['']).then();
    }, (error: any) => {
      this.toastrService.error(error.message);
    });
  }

  refreshToken(request: any, next: HttpHandler) {
    this.authService.getRefreshToken().subscribe((value: HttpResponse<any>) => {
      this.successfulLogin(value.body);
      let baseUrlLength = BASE_API.length;
      let requestToAPI = request.url.substring(0, baseUrlLength) == BASE_API;

      if (requestToAPI) {
        let authReq = request.clone({headers: request.headers.set('Authorization', 'Bearer ' + this.authService.getAccessTokenInStorage())});
        next.handle(authReq).subscribe();
      }
    }, (error: any) => {
      this.toastrService.error(error.message);
    });
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['login']).then();
  }

  private successfulLogin(token: { access_token: string, refresh_token: string }) {
    localStorage.setItem(ACCESS_TOKEN, token.access_token)
    localStorage.setItem(REFRESH_TOKEN, token.refresh_token)
  }

}
