import {Component, OnInit} from '@angular/core';
import {TaskService} from '../../task.service';
import {TaskModel} from '../../task-model';
import {Router} from '@angular/router';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {UserService} from '../../../user/user.service';
import {BaseListComponent} from '../../../../core/component/base/base-list.component';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-user-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.scss']
})
export class TaskListComponent extends BaseListComponent<TaskModel> implements OnInit {
  constructor(
    private readonly userService: UserService,
    private readonly router: Router,
    taskService: TaskService,
    ngbModal: NgbModal,
    toastrService: ToastrService
  ) {
    super(taskService, ngbModal, toastrService);
  }

  ngOnInit(): void {
    this.loadAll();
    super.deleteModalSettings = {
      title: 'Delete task',
      body: 'By clicking confirm the task will be permanently deleted.',
      successDeleteMessage: 'Task deleted successfully'
    }
  }

  override completedLoadAll(){
    this.items = this.items.map(value => new TaskModel(value));
  }
}
