import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
const AUTH_API = 'http://localhost:8080/api/auth/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }
  login(user: string, pass: string): Observable<any>
  {
    let header:HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
    const transferObject = {

      username: user,
      password: pass,
    }
    const object = JSON.stringify(transferObject);
    return this.http.post('http://localhost:8080/api/auth/login', object, {headers: header});
  }



  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password
    }, httpOptions);
  }
}