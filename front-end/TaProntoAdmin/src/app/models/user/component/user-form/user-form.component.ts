import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../user.service";
import {UserModel} from "../../user-model";
import {ToastrService} from "ngx-toastr";
import {PROFILES} from "../../../../app.constants";
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from "@angular/router";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {
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
              private readonly userService: UserService,
              private readonly toastrService: ToastrService,
              private readonly router: Router,
              private readonly route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(value => {
      const userId = value['id'];
      this.userService.findById(userId).subscribe(result => {
        const user = new UserModel(result);
        this.formGroup.patchValue(user);
      })
    });
    this.route.data.subscribe(value => {
      this.viewMode = value['viewMode'];
      if(this.viewMode) {
        this.formGroup.disable();
      }
      this.editMode = value['editMode'];
    });
  }

  save() {
    this.userService.create(new UserModel(this.formGroup.value)).subscribe(() => {
      this.router.navigate(['/users']).then();
      this.toastrService.success("User created successfully");
    }, error => {
      this.toastrService.error(error.error.message);
    });
  }
}
