import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HomeComponent } from '../home/home.component'
import { DeviceService } from '../_services/device_service/device-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-add-device-modal',
  templateUrl: './add-device-modal.component.html',
  styleUrls: ['./add-device-modal.component.scss']
})
export class AddDeviceModal implements OnInit {
  addDeviceForm: FormGroup;
  deviceTypes: any[];

  constructor(public dialogRef: MatDialogRef<AddDeviceModal>,
    @Inject(MAT_DIALOG_DATA) public data: HomeComponent,private deviceService: DeviceService,  private fb: FormBuilder) {
      
      this.addDeviceForm = this.fb.group({
        name: [null, Validators.required],
        type: [null, Validators.required]
      });
      this.deviceTypes = [{value: 'DOOR', label: 'door'}, {value: 'LIGHT', label:'light'}, {value: 'THERMOSTAT', label: 'thermostat'}, {value: 'HOME_APPLIANCE', label: 'home appliance'}]
     }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.deviceService.addDevice(this.data.selectedObject.id, this.addDeviceForm.value.name, this.addDeviceForm.value.type).subscribe({
      next: (success: any) => {
        this.dialogRef.close();
      },
      error: (error: any) => {
        console.log(error);
        this.dialogRef.close();
      },
    });
  }


  onCancel():void {
    this.dialogRef.close();
  }
}
