import {UserModel} from "../user/user-model";

export class BudgetModel {
  id?: any;
  client?: UserModel;
  collaborator?: UserModel;
  budgetDate?: Date;
  approval?: boolean;
  budgetApprovalDate?: Date;

  constructor(obj: any) {
    this.id = obj.id ? obj.id : null;
    this.client = obj.client ? obj.client : null;
    this.collaborator = obj.collaborator ? obj.collaborator : null;
    this.budgetDate = obj.budgetDate ? obj.budgetDate : null;
    this.approval = obj.approval ? obj.approval : false;
    this.budgetApprovalDate = obj.budgetApprovalDate ? obj.budgetApprovalDate : null;
  }
}
