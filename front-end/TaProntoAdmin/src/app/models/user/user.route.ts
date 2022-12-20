import {Routes} from '@angular/router';
import {UserListComponent} from "./component/user-list/user-list.component";
import {UserFormComponent} from "./component/user-form/user-form.component";

export const USER_ROUTE: Routes = [
  {
    path: '',
    component: UserListComponent,
    data: {
      authorities: [],
      pageTitle: 'User List',
    }
  },
  {
    path: 'new',
    component: UserFormComponent,
    data: {
      authorities: [],
      pageTitle: 'New user',
    }
  },
  {
    path: 'edit',
    component: UserFormComponent,
    data: {
      authorities: [],
      pageTitle: 'Edit user',
    }
  },
  {
    path: 'view',
    component: UserFormComponent,
    data: {
      authorities: [],
      pageTitle: 'View user',
    }
  }
];
