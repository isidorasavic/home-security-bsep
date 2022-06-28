import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HomeComponent } from '../home/home.component'
import { DeviceService } from '../_services/device_service/device-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BoardUserComponent } from '../board-user/board-user.component';
import { UserService } from '../_services/user.service';
@Component({
  selector: 'app-add-user-modal',
  templateUrl: './add-user-modal.component.html',
  styleUrls: ['./add-user-modal.component.scss']
})
export class AddUserModal implements OnInit {
  addUserForm: FormGroup;
  roles: any[];
  hide: boolean;

  constructor(public dialogRef: MatDialogRef<AddUserModal>,
    @Inject(MAT_DIALOG_DATA) public data: BoardUserComponent, private userService: UserService,  private fb: FormBuilder) {
      this.hide = true;
      this.addUserForm = this.fb.group({
        username: [null, Validators.required],
        password: [null, [Validators.required, Validators.minLength(8), Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')]],
        firstName: [null, Validators.required],
        lastName: [null, Validators.required],
        role: [null, Validators.required],
      });
      this.roles = [{value: 'ADMIN', label: 'Admin'}, {value: 'OWNER', label:'Owner'}, {value: 'TENANT', label: 'Tenant'}]
     }

  ngOnInit(): void {
  }


  onSubmit(): void {
    console.log("form valid: "+this.addUserForm.valid)
    const newUser = {
      username: this.addUserForm.value.username,
      password: this.addUserForm.value.password,
      firstName: this.addUserForm.value.firstName,
      lastName: this.addUserForm.value.lastname,
      role: this.addUserForm.value.role
    }
    this.userService.addUser(newUser).subscribe(
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
