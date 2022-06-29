import { Component, OnInit } from '@angular/core';
import { ObjectService } from '../_services/object_service/object-service.service'
import { TokenStorageService } from '../_services/token-storage.service';
import { Object } from '../__classes/object';
import { Message } from '../__classes/message';
import { Device } from '../__classes/device';
import { User } from '../__classes/user';
import {AddTenantModal} from '../add-tenant-modal/add-tenant-modal.component'
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { AddDeviceModal } from '../add-device-modal/add-device-modal.component';
import { Router } from '@angular/router';
import { AddUserModal } from '../add-user-modal/add-user-modal.component';
import { GenerateReportModal } from '../generate-report-modal/generate-report-modal.component';
import { Report } from '../__classes/report';
import { ReportModal } from '../report-modal/report-modal.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  selectedObject: Object;
  objectsList: Object[];
  messagesList: Message[];
  devicesList: Device[];
  selectedDevice: number;
  userRole: string;
  report: Report;
  constructor(public objectService: ObjectService, private tokenStorage: TokenStorageService, public dialog: MatDialog, public router: Router) { 
    this.objectsList = [];
    this.selectedObject = new Object();
    this.messagesList = [];
    this.devicesList = [];
    this.selectedDevice = -1;
    this.userRole = '';
    this.report = new Report();
  }

  ngOnInit(): void {
    if (this.tokenStorage.getUserRole() === "ADMIN"){
      this.router.navigate(['/user']);
    }
    this.objectService.getOwnerObjects(this.tokenStorage.getUser()).subscribe(
      (data:any) => {
        console.log('data:',data[0])
        this.objectsList = data;
        this.selectedObject = data[0];
        this.selectObject(data[0].id);
        this.userRole = this.tokenStorage.getUserRole();
      },
      (err:any) => {
        console.log((err.error).message);
      }
    );
    // setInterval(this.getDeviceMessages, 7000); 
  }

  selectObject(index:number): void {
    console.log(index);
    this.selectedObject = this.objectsList[index];
    this.objectService.getObjectMessages(this.selectedObject.id).subscribe(
      (data:any) => {
        console.log(data)
        this.messagesList = data;
        this.getDevices();
      },
      (err:any) => {
        console.log((err.error).message);
      }
    );
    
  }

  getDeviceMessages(): void {
    if(this.selectedDevice == -1){
      console.log(this.selectedDevice);
      this.objectService.getObjectMessages(this.selectedObject.id).subscribe(
        (data:any) => {
          console.log(data)
          this.messagesList = data;
        },
        (err:any) => {
          console.log((err.error).message);
        }
      );
    }
    else{
      this.objectService.getDeviceMessages(this.selectedDevice).subscribe(
      (data:any) => {
        this.messagesList = data;
      },
      (err:any) => {
        console.log((err.error).message);
      }
      );
    }
    
  }

  isUserOwner(): boolean {
    return this.userRole === 'OWNER';
  }

  isUserOwnerOfObject(): boolean {
    return this.selectedObject.owner.username === this.tokenStorage.getUser();
  }

  getDevices(): void {
    this.objectService.getObjectDevices(this.selectedObject.id).subscribe(
      (data:any) => {
        console.log('devices:', data);
        this.devicesList = data;
      },
      (err:any) => {
        console.log((err.error).message);
      }
    );
  }

  openAddTennantDialog(): void {
    const dialogRef = this.dialog.open(AddTenantModal, {
      width: '400px',
      data: {selectedObject: this.selectedObject},
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log('The dialog was closed!');
      if (result){
        window.location.reload();
      }
      
    });
  }

  openAddDeviceDialog(): void {
    const dialogRef = this.dialog.open(AddDeviceModal, {
      width: '400px',
      data: {selectedObject: this.selectedObject},
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log('The dialog was closed');
      if(result){
        this.getDevices();
      }
      
    });
  }

  generateReport(): void {
    const dialogRef = this.dialog.open(GenerateReportModal, {
      width: '400px',
      data: {selectedObject: this.selectedObject},
    });

    dialogRef.afterClosed().subscribe((response) => {
      console.log('Report: ', response);
      if (response !== null){
        this.report = response;
        const dialogRef = this.dialog.open(ReportModal, {
          width: '600px',
          data: {report: this.report},
        });
    
        dialogRef.afterClosed().subscribe(() => {
          console.log('Report was closed');
          this.report = new Report();
        });
      }
    });
  }
}


