import {Route} from '@angular/router';
import {LoginComponent} from "./component/login.component";

export const LOGIN_ROUTE: Route = {
  path: '',
  component: LoginComponent,
  data: {
    authorities: [],
    pageTitle: 'Login',
  }

};
