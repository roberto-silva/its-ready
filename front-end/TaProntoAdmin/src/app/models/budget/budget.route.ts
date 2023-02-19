import {Routes} from '@angular/router';
import {BudgetListComponent} from "./component/budget-list/budget-list.component";
import {BudgetFormComponent} from "./component/budget-form/budget-form.component";

export const BUDGET_ROUTE: Routes = [
  {
    path: '',
    component: BudgetListComponent,
    data: {
      authorities: [],
      pageTitle: 'User List',
    }
  },
  {
    path: 'new',
    component: BudgetFormComponent,
    data: {
      authorities: [],
      pageTitle: 'New user',
    }
  },
  {
    path: ':id/edit',
    component: BudgetFormComponent,
    data: {
      authorities: [],
      pageTitle: 'Edit user',
      editMode: true
    }
  },
  {
    path: ':id/view',
    component: BudgetFormComponent,
    data: {
      authorities: [],
      pageTitle: 'View user',
      viewMode: true
    }
  }
];
