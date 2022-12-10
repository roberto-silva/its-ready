import {HttpHeaders} from '@angular/common/http';

export const AUTH_API = 'http://localhost:8080/api/v1/auth';
export const httpOptions = new HttpHeaders({'Content-Type': 'application/json'});
