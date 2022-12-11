import {Injectable} from '@angular/core';
import {AuthService, UserCredentials} from "../../core/services/auth.service";
import {HttpResponse} from "@angular/common/http";
import {ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly _ACESS_TOKEN = "ACCESS_TOKEN";

  constructor(
    private readonly authService: AuthService,
    private readonly toastrService: ToastrService
  ) {
  }

  login(userCredentials: UserCredentials) {
    this.authService.getAcessToken(userCredentials).subscribe((value: HttpResponse<any>) => {
      const token = value.headers.get('Authorization') || '';
      localStorage.setItem(this._ACESS_TOKEN, token)
      this.toastrService.success("Login successful.");
    }, error => {
        this.toastrService.error(error);
    });
  }

  get token() {
    return localStorage.getItem(this._ACESS_TOKEN) || '';
  }

}
