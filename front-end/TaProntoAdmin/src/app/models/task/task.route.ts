import {Routes} from '@angular/router';
import {TaskListComponent} from "./component/budget-list/task-list.component";
import {TaskFormComponent} from "./component/budget-form/task-form.component";

export const TASK_ROUTE: Routes = [
  {
    path: '',
    component: TaskListComponent,
    data: {
      authorities: [],
      pageTitle: 'User List',
    }
  },
  {
    path: 'new',
    component: TaskFormComponent,
    data: {
      authorities: [],
      pageTitle: 'New user',
    }
  },
  {
    path: ':id/edit',
    component: TaskFormComponent,
    data: {
      authorities: [],
      pageTitle: 'Edit user',
      editMode: true
    }
  },
  {
    path: ':id/view',
    component: TaskFormComponent,
    data: {
      authorities: [],
      pageTitle: 'View user',
      viewMode: true
    }
  }
];
