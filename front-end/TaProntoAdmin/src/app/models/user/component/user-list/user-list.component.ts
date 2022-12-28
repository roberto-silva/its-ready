import {Component, OnInit} from '@angular/core';
import {UserService} from "../../user.service";
import {UserModel} from "../../user-model";
import {Router} from "@angular/router";
import {PROFILES} from "../../../../app.constants";

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
    private readonly router: Router
  ) {
  }

  ngOnInit(): void {
    this.userService.getAll(this.getParams()).subscribe((value: any) => {
      this.users = value.body.content || [];
    });
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
}
