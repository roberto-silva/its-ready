import {Component, OnInit} from '@angular/core';
import {UserService} from "../../user.service";
import {UserModel} from "../../user-model";
import {Router} from "@angular/router";
import {PROFILES} from "../../../../app.constants";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ActionModalComponent} from "../../../../shared/components/action-modal/action-modal.component";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
  users: UserModel[] = [];
  private page: number = 0;
  private pageSize: number = 20;
  private search: string = '';
  private filter: string = '';

  constructor(
    private readonly userService: UserService,
    private readonly router: Router,
    private readonly ngbModal: NgbModal,
    private readonly toastrService: ToastrService
  ) {
  }

  ngOnInit(): void {
    this.loadAll();
  }

  private getParams() {
    return {
      page: this.page,
      pageSize: this.pageSize,
      search: this.search,
      filter: this.filter
    }
  }

  goToRegistrationForm() {
    this.router.navigate(['users/new']);
  }

  getProfiles(profiles: number[]): string [] {
    return PROFILES.filter(value => profiles.includes(value.cod)).map(value => value.name) || [];
  }

  openDeleteModal(user: UserModel) {
    const modalRef = this.ngbModal.open(ActionModalComponent);
    modalRef.componentInstance.title = "Delete User"
    modalRef.componentInstance.body = "By clicking confirm the user will be permanently deleted."
    modalRef.result.then(value => {
      if(value){
        this.userService.delete(user.id).subscribe(() => {
          this.toastrService.success("User deleted successfully");
          this.loadAll();
        }, error => {
          this.toastrService.error(error.error.message);
        });
      }
    })
  }

  private loadAll() {
    this.userService.getAll(this.getParams()).subscribe((value: any) => {
      this.users = value.body.content || [];
    });
  }
}
