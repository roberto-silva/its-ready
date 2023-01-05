import {Injectable} from '@angular/core';
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ServiceUtil {
  public generateHttpParams(data: any): HttpParams {
    return Object.getOwnPropertyNames(data)
      .reduce((p, key) => p.set(key, data[key]), new HttpParams());
  }
}


