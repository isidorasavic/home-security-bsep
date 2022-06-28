import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HomeComponent } from '../home/home.component'
import { DeviceService } from '../_services/device_service/device-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../__classes/user';
import { UserService } from '../_services/user.service';
import { ObjectService } from '../_services/object_service/object-service.service';
@Component({
  selector: 'app-change-object-owner-modal',
  templateUrl: './change-object-owner-modal.component.html',
  styleUrls: ['./change-object-owner-modal.component.scss']
})
export class ChangeObjectOwnerModal implements OnInit {
  changeOwnerForm: FormGroup;
  ownersList: User[];

  constructor(public dialogRef: MatDialogRef<ChangeObjectOwnerModal>,
    @Inject(MAT_DIALOG_DATA) public data: HomeComponent,private userService: UserService, private objectService: ObjectService,  private fb: FormBuilder) {
      
      this.changeOwnerForm = this.fb.group({
        owner: [null, Validators.required]
      });
      this.ownersList = [];
     }

  ngOnInit(): void {
    this.userService.getAllOwners().subscribe({
      next: (response: any) => {
        this.ownersList = response;
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }

  onSubmit(): void {
    this.objectService.changeObjectOwner(this.data.selectedObject.id, this.changeOwnerForm.value.owner).subscribe({
      next: (success: any) => {
        this.dialogRef.close(true);
      },
      error: (error: any) => {
        console.log(error);
        this.dialogRef.close(false);
      },
    });
  }


  onCancel():void {
    this.dialogRef.close(false);
  }
}
