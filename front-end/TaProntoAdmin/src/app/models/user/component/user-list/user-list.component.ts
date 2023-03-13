import {Component, OnInit} from '@angular/core';
import {UserService} from "../../user.service";
import {UserModel} from "../../user-model";
import {Router} from "@angular/router";
import {PROFILES} from "../../../../app.constants";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ToastrService} from "ngx-toastr";
import {BaseListComponent} from "../../../../core/component/base/base-list.component";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent extends BaseListComponent<UserModel> implements OnInit {
  users: UserModel[] = [];

  constructor(
    private readonly router: Router,
    userService: UserService,
    ngbModal: NgbModal,
    toastrService: ToastrService
  ) {
    super(userService, ngbModal, toastrService);
  }

  ngOnInit(): void {
    super.loadAll();
  }

  goToRegistrationForm() {
    this.router.navigate(['users/new']).then();
  }

  getProfiles(profiles: number[]): string [] {
    return PROFILES.filter(value => profiles.includes(value.cod)).map(value => value.name) || [];
  }

}
