import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BudgetService} from "../../budget.service";
import {BudgetModel} from "../../budget-model";
import {ToastrService} from "ngx-toastr";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../../user/user.service";

@Component({
  selector: 'app-user-form',
  templateUrl: './budget-form.component.html',
  styleUrls: ['./budget-form.component.scss']
})
export class BudgetFormComponent implements OnInit {
  clients: BudgetModel[] = [];
  colaborators: BudgetModel[] = [];

  formGroup: FormGroup = this.formBuilder.group({
    id: [null, []],
    clientId: ['', Validators.required],
    collaboratorId: ['', Validators.required],
    description: ['', Validators.required],
    budgetDate: [],
    approval: [],
    budgetApprovalDate: [],
    amount: [0, Validators.required]
  });

  viewMode: boolean = false;
  editMode: boolean = false;
  title: string = 'Budget registration';

  constructor(private readonly formBuilder: FormBuilder,
              private readonly budgetService: BudgetService,
              private readonly userService: UserService,
              private readonly toastrService: ToastrService,
              private readonly router: Router,
              private readonly route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(value => {
      const budgetId = value['id'];
      if (!!budgetId) {
        this.budgetService.findById(budgetId).subscribe(result => {
          const budget = new BudgetModel(result);
          this.formGroup.patchValue(budget);
        })
      }
    });
    this.route.data.subscribe(value => {
      this.viewMode = value['viewMode'];
      if (this.viewMode) {
        this.formGroup.disable();
        this.title = 'Budget view';
      }
      this.editMode = value['editMode'];
      this.title = this.editMode ? 'Budget update' : this.title;
    });

    this.setUsersListByUserType('COSTUMER');
    this.setUsersListByUserType('ATTENDANT');
  }

  save() {
    const updateAction = this.formGroup.get('id')?.value;

    const action =  updateAction ?
      this.budgetService.update(this.formGroup.value) :
      this.budgetService.create(this.formGroup.value);

    action.subscribe(() => {
      this.router.navigate(['/budgets']).then(() => {
        const action = updateAction ? 'updated' : 'created';
        this.toastrService.success(`Budget ${action} successfully`);
      });

    }, error => {
      this.toastrService.error(error.error.message);
    });
  }

  setUsersListByUserType(type: "ATTENDANT" | "COSTUMER") {
    this.userService.getAllByUserType(type).subscribe((value: any) => {
      switch (type) {
        case 'COSTUMER':
          this.clients = [...this.clients, ...value.body];
          break;
        case 'ATTENDANT':
          this.colaborators = [...this.colaborators, ...value.body];
      }
    });
  }

  approveBudget() {
    this.formGroup.get('approval')?.setValue(true);
    this.save();
  }
}
