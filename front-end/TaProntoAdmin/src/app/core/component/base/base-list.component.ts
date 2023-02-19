import {Component} from '@angular/core';
import {ActionModalComponent} from "../../../shared/components/action-modal/action-modal.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ToastrService} from "ngx-toastr";
import {BaseService} from "../../services/base-service.service";

@Component({templateUrl: 'base-list.component.html'})
export class BaseListComponent<T> {
  deleteModalSettings?: { title: string, body: string, successDeleteMessage: string };
  paginationsSettings: { page: number, pageSize: number, search: string, filter: string } = {
    page: 0,
    pageSize: 20,
    search: '',
    filter: ''
  };
  items: T[] = [];

  constructor(protected entityService: BaseService<any>,
              protected ngbModal: NgbModal,
              protected toastrService: ToastrService
  ) {
  }

  openDeleteModal(entity: any) {
    const modalRef = this.ngbModal.open(ActionModalComponent);
    modalRef.componentInstance.title = this.deleteModalSettings?.title
    modalRef.componentInstance.body = this.deleteModalSettings?.body
    modalRef.result.then(value => {
      if (value) {
        this.entityService.delete(entity.id).subscribe(() => {
          this.toastrService.success(this.deleteModalSettings?.successDeleteMessage);
          this.loadAll();
        }, (error: any) => {
          this.toastrService.error(error.error.message);
        });
      }
    })
  }

  protected loadAll() {
    this.entityService.getAll(this.getParams()).subscribe((value: any) => {
      this.items = value.body.content || [];
    });
  }

  protected getParams() {
    return this.paginationsSettings;
  }

  setSearch(search?: string) {
    this.paginationsSettings.search = search || '';
    this.paginationsSettings.page = 0;
    this.loadAll();
  }


}
