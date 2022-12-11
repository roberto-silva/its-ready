import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {ACCESS_TOKEN, AUTH_API, httpOptions} from "../../app.constants";
import {Observable} from "rxjs";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  helper = new JwtHelperService();

  constructor(private http: HttpClient) {
  }

  getAccessToken(userCredentials: UserCredentials): Observable<any> {
    return this.http.post(AUTH_API, userCredentials,{observe: 'response', responseType: 'text'});
  }

  getRefreshToken(token: string): Observable<any> {
    return this.http.post(AUTH_API + 'refreshtoken', {
      refreshToken: token
    }, {observe: 'response', responseType: 'text'});
  }

  getToken() {
    return localStorage.getItem(ACCESS_TOKEN) || '';
  }

  setToken(newToken:string) {
    localStorage.setItem(ACCESS_TOKEN, newToken);
  }

  getEmail() {
    return this.getToken() ? this.helper.decodeToken(this.getToken()).sub : '';
  }

}

export class UserCredentials {
    constructor(email:string, password:string) {
    }
}