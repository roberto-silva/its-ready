import {HttpHeaders} from '@angular/common/http';
export const ACCESS_TOKEN = "ACCESS_TOKEN";

export const BASE_API = 'http://localhost:8080';
export const AUTH_API = BASE_API + '/api/v1/auth';
export const httpOptions = new HttpHeaders({'Content-Type': 'application/json'});
