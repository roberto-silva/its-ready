import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-hearder-list',
  templateUrl: './hearder-list.component.html',
  styleUrls: ['./hearder-list.component.scss']
})
export class HearderListComponent {
  @Input()
  title: any;

  @Input()
  entityURL: any;

  @Output()
  setSearch: EventEmitter<string> = new EventEmitter<string>();

  constructor(private readonly router: Router,) {
  }

  goToRegistrationForm() {
    this.router.navigate([`${this.entityURL}/new`]).then();
  }
}
