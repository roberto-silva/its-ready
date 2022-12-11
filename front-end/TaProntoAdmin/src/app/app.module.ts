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

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [
    HttpClientModule,
    ErrorInterceptorProvider,
    {provide: ToastrService, useClass: ToastrService}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
