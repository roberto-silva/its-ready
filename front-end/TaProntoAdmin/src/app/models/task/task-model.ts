import {UserModel} from "../user/user-model";
import {BudgetModel} from "../budget/budget-model";

export enum TaskStatusEnum {
  NOT_STARTED,
  STARTED,
  FINISHED
}

export const TaskStatusModel = [
  {status: TaskStatusEnum.NOT_STARTED, color: 'bg-danger', label: 'Not started'},
  {status: TaskStatusEnum.STARTED, color: 'bg-warning', label: 'Started'},
  {status: TaskStatusEnum.FINISHED, color: 'bg-success', label: 'Finished'}
]

export class TaskModel {
  id?: any;
  budget?: BudgetModel;
  collaborator?: UserModel;
  client?: UserModel;
  initialServiceDate?: Date;
  status: TaskStatusEnum;
  taskStatusModel: { status: TaskStatusEnum, color: string, label: string };


  constructor(obj: any) {
    this.id = obj.id ? obj.id : null;
    this.budget = obj.budget ? obj.budget : null;
    this.collaborator = obj.collaborator ? obj.collaborator : null;
    this.client = obj.budget ? obj.budget.client : null;
    this.initialServiceDate = obj.initialServiceDate ? obj.initialServiceDate : null;
    this.status = obj.status ? obj.status : TaskStatusEnum.NOT_STARTED;
    this.taskStatusModel = TaskStatusModel[this.status];
  }
}
