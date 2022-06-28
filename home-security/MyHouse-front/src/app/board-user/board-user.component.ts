import { Component, OnInit } from '@angular/core';
import { User } from '../__classes/user';
import { ObjectService } from '../_services/object_service/object-service.service'
import { TokenStorageService } from '../_services/token-storage.service';
import { Object } from '../__classes/object';
import { Message } from '../__classes/message';
import { Device } from '../__classes/device';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { AddDeviceModal } from '../add-device-modal/add-device-modal.component';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';
import { ConfirmModal } from '../confirm-modal/confirm-modal.component';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.scss']
})
export class BoardUserComponent implements OnInit {
  selectedUser: User;
  usersList: User[];
  userObjects: Object[];
  modalMessage: String;

  constructor(private userService: UserService,private objectService: ObjectService,  private tokenStorage: TokenStorageService, public dialog: MatDialog, public router: Router) { 
    this.selectedUser = new User();
    this.usersList = [];
    this.userObjects = [];
    this.modalMessage = '';
  }

  ngOnInit(): void {
    if (this.tokenStorage.getUserRole() !== "ADMIN"){
      this.router.navigate(['/home']);
    }
    this.userService.getAllUsers().subscribe(
      (response:any) => {
        console.log(response)
        this.usersList = response;
        this.selectedUser = response[0];
      },
      (err:any) => {
        console.log((err.error).message);
      }
    );
  }

  selectUser(index:number): void {
    this.selectedUser = this.usersList[index];
    this.objectService.getOwnerObjects(this.selectedUser.username).subscribe(
      (response:any) => {
        console.log(response)
        this.userObjects = response;
      },
      (err:any) => {
        console.log((err.error).message);
      }
    )
  }

  deleteUser(): void {
    console.log("prompt admin to delete user")
    console.log("delete user: "+this.selectedUser.username);
    this.modalMessage = "Are you sure you want to delete user: "+this.selectedUser.username+"?"
    const dialogRef = this.dialog.open(ConfirmModal, {
      width: '400px',
      data: {modalMessage: this.modalMessage},
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log('Confirmed: ', result);
      if (result) {
        this.userService.deleteUser(this.selectedUser.id).subscribe(
          (response:any) => {
            console.log(response)
            window.location.reload();
          },
          (err:any) => {
            console.log((err.error).message);
          }
        );
      }
    });
  }

  blockUser() : void {
    console.log("block user: "+this.selectedUser.username);
    console.log("inform that user is blocked");
    this.modalMessage = "Are you sure you want to block user: "+this.selectedUser.username+"?"
    const dialogRef = this.dialog.open(ConfirmModal, {
      width: '400px',
      data: {modalMessage: this.modalMessage},
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log('Confirmed: ', result);
    });
  }

  

}
