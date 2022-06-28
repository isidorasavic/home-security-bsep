import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HomeComponent } from '../home/home.component'
import { DeviceService } from '../_services/device_service/device-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BoardUserComponent } from '../board-user/board-user.component';
import { UserService } from '../_services/user.service';
@Component({
  selector: 'app-change-user-role-modal',
  templateUrl: './change-user-role-modal.component.html',
  styleUrls: ['./change-user-role-modal.component.scss']
})
export class ChangeUserRoleModal implements OnInit {
  changeRoleForm: FormGroup;
  roles: any[];

  constructor(public dialogRef: MatDialogRef<ChangeUserRoleModal>,
    @Inject(MAT_DIALOG_DATA) public data: BoardUserComponent, private userService: UserService,  private fb: FormBuilder) {
      this.changeRoleForm = this.fb.group({
        role: [null, Validators.required],
      });
      this.roles = [{value: 'OWNER', label:'Owner'}, {value: 'TENANT', label: 'Tenant'}]
     }

  ngOnInit(): void {
  }


  onSubmit(): void {
    this.userService.changeRole(this.data.selectedUser.id, this.changeRoleForm.value.role).subscribe(
      (response:any) => {
        console.log(response)
      },
      (err:any) => {
        console.log((err.error).message);
      }
    )
    this.dialogRef.close();
  }


  onCancel():void {
    this.dialogRef.close();
  }
}
