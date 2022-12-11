import {Injectable} from '@angular/core';
import {AuthService, UserCredentials} from "../../core/services/auth.service";
import {HttpResponse} from "@angular/common/http";
import {ToastrService} from "ngx-toastr";
import {ACCESS_TOKEN} from "../../app.constants";
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
      const token = value.headers.get('Authorization') || '';
      this.successfulLogin(token);
      this.toastrService.success("Login successful.");
      this.router.navigate(['']).then();
    }, (error: any) => {
      this.toastrService.error(error.message);
    });
  }

  refreshToken(){
    this.authService.getRefreshToken().subscribe((value: HttpResponse<any>) => {
      const token = value.headers.get('Authorization') || '';
      this.successfulLogin(token);
    }, (error: any) => {
      this.toastrService.error(error.message);
    });
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['login']).then();
  }

  private successfulLogin(token: string) {
    token = token.substring(7);
    localStorage.setItem(ACCESS_TOKEN, token)
  }

}
