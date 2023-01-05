import {NgModule} from "@angular/core";
import {CommonModule} from '@angular/common';
import {RouterModule} from "@angular/router";
import {LOGIN_ROUTE} from "./login.route";


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild([LOGIN_ROUTE])
  ]
})
export class LoginModule {
}
