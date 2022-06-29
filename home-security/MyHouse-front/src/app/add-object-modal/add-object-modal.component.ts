import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HomeComponent } from '../home/home.component'
import { DeviceService } from '../_services/device_service/device-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../__classes/user';
import { UserService } from '../_services/user.service';
import { ObjectService } from '../_services/object_service/object-service.service';
@Component({
  selector: 'app-add-object-modal',
  templateUrl: './add-object-modal.component.html',
  styleUrls: ['./add-object-modal.component.scss']
})
export class AddObjectModal implements OnInit {
  addObjectForm: FormGroup;
  objectTypes: any[];
  ownersList: User[];

  constructor(public dialogRef: MatDialogRef<AddObjectModal>,
    @Inject(MAT_DIALOG_DATA) public data: HomeComponent,private userService: UserService, private objectService: ObjectService,  private fb: FormBuilder) {
      
      this.addObjectForm = this.fb.group({
        name: [null, Validators.required],
        type: [null, Validators.required],
        owner: [null, Validators.required]
      });
      this.ownersList = [];
      this.objectTypes = [{value: 'Apartment', label: 'Apartment'}, {value: 'House', label:'House'}, {value: 'VacationHome', label: 'Vacation Home'}, {value: 'Other', label: 'Other'}]
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
    const newObject = {
      name: this.addObjectForm.value.name,
      type: this.addObjectForm.value.type, 
      owner: {
        id: this.addObjectForm.value.owner
      }
    }
    this.objectService.addObject(newObject).subscribe({
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
