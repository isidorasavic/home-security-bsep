import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  constructor(private http: HttpClient) { }

    addDevice(objectId: number, name: string, type: string): Observable<any> {
    return this.http.post(environment.BASE_PATH+"device", {
      objectId,
      name,
      type
    }, httpOptions);
  }

}