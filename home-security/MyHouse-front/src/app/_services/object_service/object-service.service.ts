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
export class ObjectService {
  constructor(private http: HttpClient) { }

    getOwnerObjects(username: string): Observable<any> {
        return this.http.get(environment.BASE_PATH+'user/'+username+'/objects', httpOptions);
    }

    getObjectMessages(objectId: number): Observable<any> {
      return this.http.get(environment.BASE_PATH+'object/'+objectId+'/messages', httpOptions);
    }

    getDeviceMessages(deviceId: number): Observable<any> {
      return this.http.get(environment.BASE_PATH+'device/'+deviceId+'/messages', httpOptions);
    }

    getObjectDevices(objectId: number): Observable<any> {
      return this.http.get(environment.BASE_PATH+'object/'+objectId+'/devices', httpOptions);
    }

}