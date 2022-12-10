import {Injectable} from '@angular/core';
import {AuthService, UserCredentials} from "../../core/services/auth.service";
import {HttpHeaders, HttpResponse} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly _ACESS_TOKEN= "ACCESS_TOKEN";

  constructor(private readonly authService: AuthService) {
  }

  login(userCredentials: UserCredentials) {
    this.authService.getAcessToken(userCredentials).subscribe((value: HttpResponse<any>) => {
      const token = value.headers.get('Authorization') || '';
      localStorage.setItem(this._ACESS_TOKEN, token)
    });
  }

  get token(){
    return localStorage.getItem(this._ACESS_TOKEN) || '';
  }

}
