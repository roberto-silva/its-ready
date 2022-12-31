import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-action-modal',
  templateUrl: './action-modal.component.html',
  styleUrls: ['./action-modal.component.scss']
})
export class ActionModalComponent implements OnInit {

  @Input() title: string = '';
  @Input() body: string = '';

  constructor(private readonly ngbActiveModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  confirmAction() {
    this.ngbActiveModal.close(true);
  }

  cancelAction(){
    this.ngbActiveModal.dismiss('Cancel action');
  }
}
