import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HomeComponent } from '../home/home.component'
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ObjectService } from '../_services/object_service/object-service.service';



@Component({
  selector: 'app-report-modal',
  templateUrl: './report-modal.component.html',
  styleUrls: ['./report-modal.component.scss']
})
export class ReportModal implements OnInit {

  constructor(public dialogRef: MatDialogRef<ReportModal>,
    @Inject(MAT_DIALOG_DATA) public data: HomeComponent) {
     }

  ngOnInit(): void {
  }


  onOk(): void {
    this.dialogRef.close();
  }

}
