import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BudgetListComponent} from './component/budget-list/budget-list.component';
import {BudgetFormComponent} from './component/budget-form/budget-form.component';
import {RouterModule} from "@angular/router";
import {BUDGET_ROUTE} from "./budget.route";
import {SharedModule} from "../../shared/shared.module";
import {ReactiveFormsModule} from "@angular/forms";
import {NgSelectModule} from "@ng-select/ng-select";
import {NgbDropdownModule} from "@ng-bootstrap/ng-bootstrap";


@NgModule({
  declarations: [
    BudgetListComponent,
    BudgetFormComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(BUDGET_ROUTE),
    SharedModule,
    ReactiveFormsModule,
    NgSelectModule,
    NgbDropdownModule
  ]
})
export class BudgetModule {
}
