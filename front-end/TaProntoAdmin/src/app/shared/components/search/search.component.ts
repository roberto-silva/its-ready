import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  @Input() title = '';
  @Output() search: EventEmitter<string> = new EventEmitter<string>();
  value: string = '';

  constructor() {
  }

  ngOnInit(): void {
  }

  emitSearchEvent() {
    this.search?.emit(this.value);
  }

}
