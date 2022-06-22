import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HomeComponent } from '../home/home.component'
import { DeviceService } from '../_services/device_service/device-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-add-tenant-modal',
  templateUrl: './add-tenant-modal.component.html',
  styleUrls: ['./add-tenant-modal.component.scss']
})
export class AddTenantModal implements OnInit {
  // addEmployeeForm: FormGroup;

  constructor(public dialogRef: MatDialogRef<AddTenantModal>,
    @Inject(MAT_DIALOG_DATA) public data: HomeComponent,private deviceService: DeviceService) { }

  ngOnInit(): void {
  }



  onCancel():void {
    this.dialogRef.close();
  }
}
