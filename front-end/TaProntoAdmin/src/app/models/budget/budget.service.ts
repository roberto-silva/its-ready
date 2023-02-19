import {Injectable} from '@angular/core';
import {BaseService} from "../../core/services/base-service.service";
import {Observable} from "rxjs";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {BudgetModel} from "./budget-model";
import {BUDGET_API} from "../../app.constants";
import {ServiceUtil} from "../../core/services/service-util.service";

@Injectable({
  providedIn: 'root'
})
export class BudgetService extends ServiceUtil implements BaseService<BudgetModel> {

  constructor(private readonly httpClient: HttpClient) {
    super();
  }

  getAll(params: any): Observable<HttpResponse<BudgetModel[]>> {
    const options = this.generateHttpParams(params);
    return this.httpClient.get<BudgetModel[]>(`${BUDGET_API}`, {params: options, observe: 'response'});
  }

  findById(id: any): Observable<BudgetModel> {
    return this.httpClient.get<BudgetModel>(`${BUDGET_API}/${id}`);
  }

  create(entity: BudgetModel): Observable<BudgetModel> {
    return this.httpClient.post<BudgetModel>(BUDGET_API, entity);
  }

  update(entity: BudgetModel): Observable<BudgetModel> {
    return this.httpClient.put<BudgetModel>(`${BUDGET_API}/${entity.id}`, entity);
  }

  delete(id: any): Observable<BudgetModel> {
    return this.httpClient.delete<BudgetModel>(`${BUDGET_API}/${id}`);
  }
}
