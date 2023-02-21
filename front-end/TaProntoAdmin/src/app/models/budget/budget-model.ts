import {UserModel} from "../user/user-model";

export class BudgetModel {
  id?: any;
  client?: UserModel;
  clientId?: UserModel;
  collaborator?: UserModel;
  collaboratorId?: UserModel;
  description?: string;
  budgetDate?: Date;
  approval?: boolean;
  amount?: boolean;
  budgetApprovalDate?: Date;

  constructor(obj: any) {
    this.id = obj.id ? obj.id : null;
    this.client = obj.client ? obj.client : null;
    this.clientId = obj.client ? obj.client.id : null;
    this.collaborator = obj.collaborator ? obj.collaborator : null;
    this.collaboratorId = obj.collaborator ? obj.collaborator.id : null;
    this.description = obj.description ? obj.description : null;
    this.budgetDate = obj.budgetDate ? obj.budgetDate : null;
    this.approval = obj.approval ? obj.approval : false;
    this.amount = obj.amount ? obj.amount : false;
    this.budgetApprovalDate = obj.budgetApprovalDate ? obj.budgetApprovalDate : null;
  }
}
