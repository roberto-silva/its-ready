import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BudgetService} from "../../budget.service";
import {BudgetModel} from "../../budget-model";
import {ToastrService} from "ngx-toastr";
import {PROFILES} from "../../../../app.constants";
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
    name: ['', Validators.required],
    cpf: ['', Validators.required],
    phone: ['', Validators.required],
    email: ['', Validators.required],
    address: this.formBuilder.group({
      id: [''],
      cep: ['', Validators.required],
      city: ['', Validators.required],
      district: ['', Validators.required],
      street: ['', Validators.required],
      referencePoint: ['', Validators.required],
      activated: [true, Validators.required]
    }),
    password: ['', Validators.required],
    profile: ['', Validators.required],
    activated: [true, Validators.required]
  });
  profiles: { name: string, cod: number } [] = PROFILES;
  viewMode: boolean = false;
  editMode: boolean = false;

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
      const userId = value['id'];
      if (!!userId) {
        this.userService.findById(userId).subscribe(result => {
          const user = new BudgetModel(result);
          this.formGroup.patchValue(user);
        })
      }
    });
    this.route.data.subscribe(value => {
      this.viewMode = value['viewMode'];
      if (this.viewMode) {
        this.formGroup.disable();
      }
      this.editMode = value['editMode'];
    });

    this.setUsersListByUserType('CLIENT');
    this.setUsersListByUserType('COLABORATOR');
  }

  save() {
    this.userService.create(new BudgetModel(this.formGroup.value)).subscribe(() => {
      this.router.navigate(['/users']).then();
      this.toastrService.success("User created successfully");
    }, error => {
      this.toastrService.error(error.error.message);
    });
  }

  setUsersListByUserType(type: 'CLIENT' | 'COLABORATOR') {
    this.userService.getAllByUserType(type).subscribe((value: any) => {
      switch (type) {
        case 'CLIENT':
          this.clients = value.body.content || [];
          break;
        case 'COLABORATOR':
          this.colaborators = value.body.content || [];
      }
    });
  }
}
