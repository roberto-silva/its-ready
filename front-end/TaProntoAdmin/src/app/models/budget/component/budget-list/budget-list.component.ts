import {Component, OnInit} from '@angular/core';
import {BudgetService} from '../../budget.service';
import {BudgetModel} from '../../budget-model';
import {Router} from '@angular/router';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {UserService} from '../../../user/user.service';
import {BaseListComponent} from '../../../../core/component/base/base-list.component';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-user-list',
  templateUrl: './budget-list.component.html',
  styleUrls: ['./budget-list.component.scss']
})
export class BudgetListComponent extends BaseListComponent<BudgetModel> implements OnInit {
  constructor(
    private readonly userService: UserService,
    private readonly router: Router,
    budgetService: BudgetService,
    ngbModal: NgbModal,
    toastrService: ToastrService
  ) {
    super(budgetService, ngbModal, toastrService);
  }

  ngOnInit(): void {
    this.loadAll();
    super.deleteModalSettings = {
      title: 'Delete User',
      body: 'By clicking confirm the user will be permanently deleted.',
      successDeleteMessage: 'User deleted successfully'
    }

  }
}
