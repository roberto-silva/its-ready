import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TaskListComponent} from './component/budget-list/task-list.component';
import {TaskFormComponent} from './component/budget-form/task-form.component';
import {RouterModule} from "@angular/router";
import {TASK_ROUTE} from "./task.route";
import {SharedModule} from "../../shared/shared.module";
import {ReactiveFormsModule} from "@angular/forms";
import {NgSelectModule} from "@ng-select/ng-select";
import {NgbDropdownModule} from "@ng-bootstrap/ng-bootstrap";


@NgModule({
  declarations: [
    TaskListComponent,
    TaskFormComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(TASK_ROUTE),
    SharedModule,
    ReactiveFormsModule,
    NgSelectModule,
    NgbDropdownModule
  ]
})
export class TaskModule {
}
