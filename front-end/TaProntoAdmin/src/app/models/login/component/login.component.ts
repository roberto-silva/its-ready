import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {LoginService} from "../login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userCredentialsForm: FormGroup = this.formBuilder.group({
    username: ['admin@gmail.com'],
    password: ['Admin@123']
  });

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly loginService: LoginService
  ) {
  }

  ngOnInit(): void {
  }

  login() {
    this.loginService.login(this.userCredentialsForm.value)
  }
}
