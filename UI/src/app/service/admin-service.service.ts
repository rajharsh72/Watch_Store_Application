import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserServiceService } from './user-service.service';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  constructor(
    private _httpClient:HttpClient,
    private _userService: UserServiceService
  ) { }


  API_URL = "http://localhost:9090/api"

  addWatch(watchData:any):Observable<any>{

    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + this._userService.getToken()
    });

    return this._httpClient.post(this.API_URL + '/watch', watchData, {headers});
  }


  updateWatch(watchData:any, watchId:any):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + this._userService.getToken()
    });

    return this._httpClient.put(`${this.API_URL}/watch/${watchId}`, watchData, {headers});
  }

  markOutOfStock(watchId:any):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + this._userService.getToken()
    });

    console.log(this._userService.getCurrentRole(), this._userService.getToken());

    return this._httpClient.put(`${this.API_URL}/watch/stock/${watchId}`,{},{headers});
  }

  getStockStats(){
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + this._userService.getToken()
    });

    return this._httpClient.get(this.API_URL + '/admin/stock-stats', {headers});
  }

  getAllOrders():Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + this._userService.getToken()
    });

    return this._httpClient.get(this.API_URL + '/order', {headers});
  }

  updateOrderStatus(orderId:any, newOrderStatus:string):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + this._userService.getToken()
    });

    const params = new HttpParams().set('newOrderStatus', newOrderStatus);

    return this._httpClient.put(`${this.API_URL}/order/${orderId}`,null,{headers,params});
  }




}
