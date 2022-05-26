import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../__classes/user';


@Injectable({
  providedIn: 'root'
})
export class UserCRUDService {
  API_URL_ADMIN = 'http://localhost:8080/api/user';
  API_URL_HOME = 'http://localhost:8081/api/user';

  constructor(private http: HttpClient) { }

  addUser(user: User): Observable<any>{  
    console.log(user)
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    return this.http.post(this.API_URL_ADMIN + '/addUser', user,{headers: headers});
  }

  listUsers(): Observable<any>{  
    const headers = new HttpHeaders();
    headers.append('Accept', 'application/json');
    return this.http.get(this.API_URL_HOME + '/list',{headers: headers});
  }

  deleteUser(id:string): Observable<any>{  
    const headers = new HttpHeaders();
    headers.append('Accept', 'application/json');
    return this.http.delete(this.API_URL_HOME + '/delete/'+id,{headers: headers});
  }

  searchUser(id:string): Observable<any>{  
    const headers = new HttpHeaders();
    headers.append('Accept', 'application/json');
    return this.http.get(this.API_URL_HOME + '/search/'+id,{headers: headers});
  }

  changeRoleUser(id:string,role:string): Observable<any>{  
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    return this.http.put(this.API_URL_HOME + '/changeRole',{},{params:{"username":id,"newRole":role}});
  }
}
