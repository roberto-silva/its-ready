import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavbarComponent} from './core/component/navbar/navbar.component';
import {FooterComponent} from './core/component/footer/footer.component';
import {LoginComponent} from './models/login/component/login.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {ErrorInterceptorProvider} from "../interceptors/error-interceptor";
import {ToastrModule, ToastrService} from "ngx-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AuthInterceptorProvider} from "../interceptors/auth-interceptor";
import { HomeComponent } from './models/home/component/home.component';
import {UserAutenticated} from "../services/user-autenticated";
import { SidebarComponent } from './core/component/sidebar/sidebar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

export const TOAST_PROVIDER = {provide: ToastrService, useClass: ToastrService};
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    HomeComponent,
    SidebarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
    NgbModule
  ],
  providers: [
    HttpClientModule,
    AuthInterceptorProvider,
    ErrorInterceptorProvider,
    TOAST_PROVIDER,
    UserAutenticated
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
