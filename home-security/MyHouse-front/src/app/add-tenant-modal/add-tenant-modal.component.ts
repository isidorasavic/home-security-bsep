import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HomeComponent } from '../home/home.component'
import { DeviceService } from '../_services/device_service/device-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../__classes/user';
import { ObjectService } from '../_services/object_service/object-service.service';
@Component({
  selector: 'app-add-tenant-modal',
  templateUrl: './add-tenant-modal.component.html',
  styleUrls: ['./add-tenant-modal.component.scss']
})
export class AddTenantModal implements OnInit {
  addTenantForm: FormGroup;
  potentialTenants: User[];

  constructor(public dialogRef: MatDialogRef<AddTenantModal>,
    @Inject(MAT_DIALOG_DATA) public data: HomeComponent,  private fb: FormBuilder,private deviceService: DeviceService, private objectService: ObjectService) {
      this.potentialTenants = [];
      this.addTenantForm = this.fb.group({
        tenant: [null, Validators.required],
      });
     }

  ngOnInit(): void {
    this.objectService.getPotentialTenants(this.data.selectedObject.id).subscribe({
      next: (data: any) => {
        this.potentialTenants = data;
        console.log(data);
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }

  onSubmit(): void {
    this.objectService.addTenant(this.data.selectedObject.id, this.addTenantForm.value.tenant).subscribe({
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
