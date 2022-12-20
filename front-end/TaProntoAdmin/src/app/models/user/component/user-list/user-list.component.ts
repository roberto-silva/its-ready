import { Component, OnInit } from '@angular/core';
import {UserService} from "../../user.service";
import {UserModel} from "../../user-model";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
  users:UserModel[] = [];
  private page: number = 0;
  private pageSize: number = 20;
  private search: string = '';
  private filter: string = '';
  constructor(private readonly userService:UserService) { }

  ngOnInit(): void {
    this.userService.getAll(this.getParams()).subscribe((value: any) => {
      this.users = value.body.content || [];
    });
  }

  private getParams() {
      return {
        page: this.page,
        pageSize : this.pageSize,
        search: this.search,
        filter:this.filter
      }
  }
}
