import {Injectable} from '@angular/core';
import {AuthService, UserCredentials} from "../../core/services/auth.service";
import {HttpResponse} from "@angular/common/http";
import {ToastrService} from "ngx-toastr";
import {ACCESS_TOKEN, REFRESH_TOKEN} from "../../app.constants";
import {Router} from "@angular/router";

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

  logout() {
    localStorage.clear();
    this.router.navigate(['login']).then();
  }

  successfulLogin(token: { access_token: string, refresh_token: string }) {
    localStorage.setItem(ACCESS_TOKEN, token.access_token)
    localStorage.setItem(REFRESH_TOKEN, token.refresh_token)
  }

}
