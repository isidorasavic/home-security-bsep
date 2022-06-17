import { Component, OnInit } from '@angular/core';
import { ObjectService } from '../_services/object_service/object-service.service'
import { TokenStorageService } from '../_services/token-storage.service';
import { Object } from '../__classes/object';
import { User } from '../__classes/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  selectedObject: any;
  objectsList: Object[];
  constructor(private objectService: ObjectService, private tokenStorage: TokenStorageService) { 
    this.objectsList = [];
  }

  ngOnInit(): void {
    this.objectService.getOwnerObjects(this.tokenStorage.getUser()).subscribe(
      (data:any) => {
        console.log('data:',data)
        this.objectsList = data;
        this.selectedObject = this.objectsList[0];
      },
      (err:any) => {
        console.log((err.error).message);
      }
    );
  }

  selectObject(index:number): void {
    // this.selectedObject = obj;
    console.log(index);
    this.selectedObject = this.objectsList[index];
    // console.log(this.objectsList[0]);
  }
}