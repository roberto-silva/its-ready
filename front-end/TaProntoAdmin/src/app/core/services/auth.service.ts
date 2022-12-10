import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {AUTH_API, httpOptions} from "../../app.constants";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(private http: HttpClient) {
  }

  getAcessToken(userCredentials: UserCredentials): Observable<any> {
    return this.http.post(AUTH_API, userCredentials,{observe: 'response'});
  }

  refreshToken(token: string): Observable<any> {

    return this.http.post(AUTH_API + 'refreshtoken', {
      refreshToken: token
    });
  }
}

export class UserCredentials {
    constructor(email:string, password:string) {
    }
}
