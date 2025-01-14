import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(
    private _hhtpClient: HttpClient
  ) { }

  API_URL = "http://localhost:9090/api/"
  requestHeader = new HttpHeaders({
    'No-Auth': 'True'
  });

  login(loginData: any):Observable<any>{
    return this._hhtpClient.post(this.API_URL + 'auth/login', loginData);
  }
}
