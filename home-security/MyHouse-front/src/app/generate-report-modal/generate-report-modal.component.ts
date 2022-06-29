import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HomeComponent } from '../home/home.component'
import { DeviceService } from '../_services/device_service/device-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BoardUserComponent } from '../board-user/board-user.component';
import { UserService } from '../_services/user.service';
import { ObjectService } from '../_services/object_service/object-service.service';



@Component({
  selector: 'app-generate-report-modal',
  templateUrl: './generate-report-modal.component.html',
  styleUrls: ['./generate-report-modal.component.scss']
})
export class GenerateReportModal implements OnInit {
  reportForm: FormGroup;

  constructor(public dialogRef: MatDialogRef<GenerateReportModal>,
    @Inject(MAT_DIALOG_DATA) public data: HomeComponent, private objectService: ObjectService,  private fb: FormBuilder) {
      this.reportForm = this.fb.group({
        dateFrom: [null, Validators.required],
        dateTo: [null, Validators.required]
      });
     }

  ngOnInit(): void {
  }


  validDates(): boolean {
    return this.reportForm.value.dateFrom !== null && this.reportForm.value.dateTo !== null
  }

  onSubmit(): void {
   if (this.reportForm.value.dateFrom === null || this.reportForm.value.dateTo === null){
    console.log("both dates are required!")
   }
    else{
      console.log("Dates are ok :)")
      let startDate = this.reportForm.value.dateFrom.toString().substring(4, 15);
      let endDate = this.reportForm.value.dateTo.toString().substring(4, 15);
      this.objectService.getReport(this.data.selectedObject.id, startDate, endDate).subscribe(
        (response:any) => {
          console.log(response);
          this.dialogRef.close(response);
        },
        (err:any) => {
          console.log((err.error).message);
        }
      )
    }
  }
  onCancel():void {
    this.dialogRef.close(null);
  }
}
