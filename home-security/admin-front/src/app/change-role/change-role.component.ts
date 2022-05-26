import { JsonpClientBackend } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserCRUDService } from '../_services/user-crud.service';
@Component({
  selector: 'app-change-role',
  templateUrl: './change-role.component.html',
  styleUrls: ['./change-role.component.scss']
})
export class ChangeRoleComponent implements OnInit {

  profileForm = new FormGroup({
    username: new FormControl(''),
    role: new FormControl(''),
  });
  

  constructor(private service: UserCRUDService) { }

  ngOnInit(): void {
  }

  addUser(){
    let formObj = this.profileForm.getRawValue();
    if(formObj.username == "" || formObj.role == "")
    {
      alert("POPUNI SVA POLJA!");
    }
    else{
    this.service.changeRoleUser(formObj.username, formObj.role).subscribe({
      next: data => {
        console.log(data);
        alert("Changed!");
    },
    error: err => {
      console.log(err);
      alert("Error!");
    }
  });
  }
  }
}
