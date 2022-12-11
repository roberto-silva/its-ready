import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserAutenticated} from "../services/user-autenticated";

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./models/home/home.module').then((mod) => mod.HomeModule),
    canActivate: [UserAutenticated]
  },
  {
    path: '',
    loadChildren: () => import('./models/login/login.module').then((mod) => mod.LoginModule),
    canActivate: []
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
