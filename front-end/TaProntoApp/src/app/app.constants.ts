import {HttpHeaders} from '@angular/common/http';

export const AUTH_API = 'http://localhost:8080/api/auth/';
export const httpOptions = {
  headers: new HttpHeaders({
    // eslint-disable-next-line @typescript-eslint/naming-convention
    'Content-Type': 'application/json'
  })
};
