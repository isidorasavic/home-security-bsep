import { Component, OnInit } from '@angular/core';
import { ObjectService } from '../_services/object_service/object-service.service'
import { TokenStorageService } from '../_services/token-storage.service';
import { Object } from '../__classes/object';
import { Message } from '../__classes/message';
import { Device } from '../__classes/device';
import { User } from '../__classes/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  selectedObject: any;
  objectsList: Object[];
  messagesList: Message[];
  devicesList: Device[];
  selectedDevice: number;
  constructor(private objectService: ObjectService, private tokenStorage: TokenStorageService) { 
    this.objectsList = [];
    this.messagesList = [];
    this.devicesList = [];
    this.selectedDevice = -1;
  }

  ngOnInit(): void {
    this.objectService.getOwnerObjects(this.tokenStorage.getUser()).subscribe(
      (data:any) => {
        console.log('data:',data[0])
        this.objectsList = data;
        this.selectedObject = data[0];
        this.selectObject(data[0].id);
      },
      (err:any) => {
        console.log((err.error).message);
      }
    );
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
    if(this.selectedDevice === -1){
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
}