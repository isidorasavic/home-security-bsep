import { JsonpClientBackend } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserCRUDService } from '../_services/user-crud.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {
  profileForm = new FormGroup({
    username: new FormControl(''),
    role: new FormControl(''),
    password: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
  });
  

  constructor(private service: UserCRUDService) { }

  ngOnInit(): void {
  }

  addUser(){
    let formObj = this.profileForm.getRawValue();
    if(formObj.username == "" || formObj.role == "" || formObj.password == "" || formObj.firstName == "" || formObj.lastName == "" )
    {
      alert("POPUNI SVA POLJA!");
    }
    else{
    this.service.addUser(formObj).subscribe({
      next: data => {
        console.log(data);
        alert("Added!");
    },
    error: err => {
      console.log(err);
      alert("Error!");
    }
  });
  }
  }
}
