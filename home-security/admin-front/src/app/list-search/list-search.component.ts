import { Component, OnInit } from '@angular/core';
import { UserCRUDService } from '../_services/user-crud.service';
import { UserLong } from '../__classes/userLong';
@Component({
  selector: 'app-list-search',
  templateUrl: './list-search.component.html',
  styleUrls: ['./list-search.component.scss']
})
export class ListSearchComponent implements OnInit {
  Users: UserLong[] = [];
  placeId: string = "";

  constructor(private service: UserCRUDService) { }

  ngOnInit(): void {
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

  onClickSearch(): void
  {

    let id = ((document.getElementById("barSearch") as HTMLInputElement).value);
    if(id=="")
    {
      this.Users = [];
    }
    else
    {
    this.service.searchUser(id).subscribe({
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

  }
}
