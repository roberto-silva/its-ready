import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.css']
})
export class SideMenuComponent implements OnInit {

  public appPages = [
    { title: 'Services', url: '/services', icon: 'hammer' }
  ];
  public labels = ['Settings', 'About'];

  constructor() { }

  ngOnInit(): void {
  }

}
