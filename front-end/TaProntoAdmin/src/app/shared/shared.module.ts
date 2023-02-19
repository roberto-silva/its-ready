import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SearchComponent} from './components/search/search.component';
import { ActionModalComponent } from './components/action-modal/action-modal.component';
import {FormsModule} from "@angular/forms";
import { HearderListComponent } from './components/hearder-list/hearder-list.component';


@NgModule({
  declarations: [
    SearchComponent,
    ActionModalComponent,
    HearderListComponent
  ],
    imports: [
        CommonModule,
        FormsModule
    ],
  exports: [
    SearchComponent,
    HearderListComponent,
  ]
})
export class SharedModule {
}
