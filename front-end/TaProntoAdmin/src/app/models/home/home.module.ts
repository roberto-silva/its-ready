import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from "@angular/router";
import {HOME_ROUTE} from "./home.route";


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild([HOME_ROUTE])
  ]
})
export class HomeModule {
}
