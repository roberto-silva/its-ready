import {Injectable} from '@angular/core';
import {BaseService} from "../../core/services/base-service.service";
import {Observable} from "rxjs";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {UserModel} from "./user-model";
import {USER_API} from "../../app.constants";
import {ServiceUtil} from "../../core/services/service-util.service";
import {BudgetModel} from "../budget/budget-model";

@Injectable({
  providedIn: 'root'
})
export class UserService extends ServiceUtil implements BaseService<UserModel> {

  constructor(private readonly httpClient: HttpClient) {
    super();
  }

  getAll(params: any): Observable<HttpResponse<UserModel[]>> {
    const options = this.generateHttpParams(params);
    return this.httpClient.get<UserModel[]>(`${USER_API}`, {params: options, observe: 'response'});
  }

  findById(id: any): Observable<UserModel> {
    return this.httpClient.get<UserModel>(`${USER_API}/${id}`);
  }

  create(entity: UserModel): Observable<UserModel> {
    return this.httpClient.post<UserModel>(USER_API, entity);
  }

  update(entity: UserModel): Observable<UserModel> {
    return this.httpClient.put<UserModel>(`${USER_API}/${entity.id}`, entity);
  }

  delete(id: any): Observable<UserModel> {
    return this.httpClient.delete<UserModel>(`${USER_API}/${id}`);
  }

  getAllByUserType(type: "CLIENT" | "COLABORATOR"): Observable<HttpResponse<BudgetModel[]>>  {
    return this.httpClient.get<BudgetModel[]>(`${USER_API}/${type}`, {observe: 'response'});
  }
}
