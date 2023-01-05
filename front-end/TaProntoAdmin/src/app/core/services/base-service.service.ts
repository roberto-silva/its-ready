import {HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';

@Injectable()
export abstract class BaseService<T> {

  abstract getAll(params: any): Observable<HttpResponse<T[]>>;

  abstract findById(id: any): Observable<T>;

  abstract create(entity: T): Observable<T>;

  abstract update(entity: T): Observable<T>;

  abstract delete(id: any): Observable<T>;

}
