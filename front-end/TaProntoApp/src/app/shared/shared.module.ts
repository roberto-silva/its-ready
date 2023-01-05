import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SideMenuComponent} from './side-menu/side-menu.component';
import {IonicModule} from "@ionic/angular";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    SideMenuComponent
  ],
  exports: [
    SideMenuComponent
  ],
  imports: [
    CommonModule,
    IonicModule,
    RouterModule
  ]
})
export class SharedModule {
}
