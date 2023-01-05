import {Component, OnInit} from '@angular/core';
import {NavController} from "@ionic/angular";

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.css']
})
export class LoginPage implements OnInit {

  constructor(
    private readonly navCtrl: NavController
  ) {
  }

  ngOnInit(): void {
  }

  login() {
    this.navCtrl.navigateRoot('/services').then();
  }

}
