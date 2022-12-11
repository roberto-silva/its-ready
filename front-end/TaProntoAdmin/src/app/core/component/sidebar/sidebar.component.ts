import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../../models/login/login.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  constructor(private readonly loginService: LoginService) { }

  ngOnInit(): void {
  }

  logout() {
    this.loginService.logout();
  }
}
