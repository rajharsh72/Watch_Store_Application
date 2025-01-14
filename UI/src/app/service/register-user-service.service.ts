import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterUserServiceService {

  constructor(
    private _httpClient:HttpClient
  ) { }

  API_URL = "http://localhost:9090/api/"
  requestHeader = new HttpHeaders({
    'No-Auth': 'True'
  });

  registerUser(registerData:any):Observable<any>{
    return this._httpClient.post(this.API_URL + 'user/register', registerData);
  }
}
