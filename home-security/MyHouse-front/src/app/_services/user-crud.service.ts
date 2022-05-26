import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../__classes/user';


@Injectable({
  providedIn: 'root'
})
export class UserCRUDService {
  API_URL = 'http://localhost:8081/api/user';

  constructor(private http: HttpClient) { }

  addUser(user: User): Observable<any>{  
    console.log(user)
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    return this.http.post(this.API_URL + '/addUser', user,{headers: headers});
  }
}
