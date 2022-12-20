import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ACCESS_TOKEN, AUTH_API, REFRESH_TOKEN} from "../../app.constants";
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
    return this.http.post(AUTH_API, userCredentials, {observe: 'response', responseType: 'json'});
  }

  getRefreshToken(): Observable<any> {
    const headers = {
      Authorization: 'Bearer ' + this.getRefreshTokenInStorage()
    };
    return this.http.get(AUTH_API + '/token/refresh', {observe: 'response', responseType: 'json', headers: headers},);
  }

  getAccessTokenInStorage() {
    return localStorage.getItem(ACCESS_TOKEN) || '';
  }

  getRefreshTokenInStorage() {
    return localStorage.getItem(REFRESH_TOKEN) || '';
  }

  setAccessTokenInStorage(newToken: string) {
    localStorage.setItem(ACCESS_TOKEN, newToken);
  }

  setRefreshTokenInStorage(newToken: string) {
    localStorage.setItem(REFRESH_TOKEN, newToken);
  }

  getEmail() {
    return this.getAccessTokenInStorage() ? this.helper.decodeToken(this.getAccessTokenInStorage()).sub : '';
  }

  refreshTokenExpired(){
    return this.helper.isTokenExpired(this.getRefreshTokenInStorage());
  }

}

export class UserCredentials {
  constructor(email: string, password: string) {
  }
}
