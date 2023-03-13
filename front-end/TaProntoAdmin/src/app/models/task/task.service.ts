import {Injectable} from '@angular/core';
import {BaseService} from "../../core/services/base-service.service";
import {Observable, of} from "rxjs";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {TaskModel, TaskStatusEnum, TaskStatusModel} from "./task-model";
import {TASK_API} from "../../app.constants";
import {ServiceUtil} from "../../core/services/service-util.service";

@Injectable({
  providedIn: 'root'
})
export class TaskService extends ServiceUtil implements BaseService<TaskModel> {

  constructor(private readonly httpClient: HttpClient) {
    super();
  }

  getAll(params: any): Observable<HttpResponse<TaskModel[]>> {
    const options = this.generateHttpParams(params);
    return this.httpClient.get<TaskModel[]>(`${TASK_API}`, {params: options, observe: 'response'});
  }

  findById(id: any): Observable<TaskModel> {
    return this.httpClient.get<TaskModel>(`${TASK_API}/${id}`);
  }

  create(entity: TaskModel): Observable<TaskModel> {
    return of({status: TaskStatusEnum.NOT_STARTED, taskStatusModel:TaskStatusModel[TaskStatusEnum.NOT_STARTED]});
  }

  update(entity: TaskModel): Observable<TaskModel> {
    return this.httpClient.put<TaskModel>(`${TASK_API}/${entity.id}`, entity);
  }

  delete(id: any): Observable<TaskModel> {
    return this.httpClient.delete<TaskModel>(`${TASK_API}/${id}`);
  }
}
