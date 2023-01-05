import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-services',
  templateUrl: './services.page.html',
  styleUrls: ['./services.page.scss'],
})
export class ServicesPage implements OnInit {
  services: any[] = [
    {
      name: "Name",
      costumer: "Costumer",
      description: "Description"
    }
  ];

  constructor() { }

  ngOnInit() {
  }

}
