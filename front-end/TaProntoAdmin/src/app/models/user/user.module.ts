import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserListComponent} from './component/user-list/user-list.component';
import {UserFormComponent} from './component/user-form/user-form.component';
import {RouterModule} from "@angular/router";
import {USER_ROUTE} from "./user.route";
import {SharedModule} from "../../shared/shared.module";
import {ReactiveFormsModule} from "@angular/forms";
import {NgSelectModule} from "@ng-select/ng-select";
import {NgbDropdownModule} from "@ng-bootstrap/ng-bootstrap";


@NgModule({
  declarations: [
    UserListComponent,
    UserFormComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(USER_ROUTE),
    SharedModule,
    ReactiveFormsModule,
    NgSelectModule,
    NgbDropdownModule
  ]
})
export class UserModule {
}
