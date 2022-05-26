import { Component, OnInit } from '@angular/core';
import { UserCRUDService } from '../_services/user-crud.service';
import { UserLong } from '../__classes/userLong';
@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.scss']
})
export class ListUsersComponent implements OnInit {
  Users: UserLong[] = [];
  placeId: string = "";

  constructor(private service: UserCRUDService) { }

  ngOnInit(): void {
    this.service.listUsers().subscribe({
      next: data => {
        console.log(data);
        this.Users = data;
    },
    error: err => {
      console.log(err);
      alert("Error!");
    }
  });
  }

  onClickDelete($event:any, id: string): void
  {
    console.log($event.target.id)
    this.service.deleteUser(id).subscribe({
      next: data => {
        console.log(data);
        window.location.reload();
    },
    error: err => {
      console.log(err);
      alert("Error!");
    }
  });

  }

  onClickSearch($event:any, id: string): void
  {
    console.log($event.target.id)
    this.service.deleteUser(id).subscribe({
      next: data => {
        console.log(data);
        window.location.reload();
    },
    error: err => {
      console.log(err);
      alert("Error!");
    }
  });

  }

}
