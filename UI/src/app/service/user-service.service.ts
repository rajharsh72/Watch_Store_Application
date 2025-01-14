import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(
    private _httpClient: HttpClient
  ) { }


  API_URL = "http://localhost:9090/api"
  
  public setRoles(roles:[]){
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles():[]{
    return JSON.parse(localStorage.getItem('roles')!);
  }

  public setToken(jwtToken:string){
    localStorage.setItem("jwtToken", jwtToken);
  }

  public getToken():string{
    return localStorage.getItem('jwtToken')!;
  }

  public setCurrentRole(role:string){
    localStorage.setItem('currentRole', role);
  }

  public getCurrentRole():string{
    return localStorage.getItem('currentRole')!;
  }


  public clearStorage(){
    localStorage.clear();
  }
  
  public setCurrentUser(username:string){
    localStorage.setItem('currentUser', username);
  }

  public getCurrentUser():string{
    return localStorage.getItem('currentUser')!;
  }

  public setUserAddress(address:any){
    localStorage.setItem('userAddress', JSON.stringify(address));
  }

  public getUserAddress(){
    return localStorage.getItem('userAddress')!;
  }


  getAllWatches():Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer '+this.getToken()
    });

    return this._httpClient.get(this.API_URL + '/watch' , {headers});
  }

  getAllWatchesBasedOnLatestArrival():Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer '+this.getToken()
    });

    return this._httpClient.get(this.API_URL + '/watch/sort' , {headers});
  }

  getWatchByWatchId(watchId:any):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer '+this.getToken()
    });

    return this._httpClient.get(`${this.API_URL}/watch/${watchId}`,{headers});
  }

  addToCart(addWatchDto:any):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer '+this.getToken()
    });

    return this._httpClient.post(this.API_URL + '/cart/add', addWatchDto, {headers});
  }

  getUserCart():Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer '+this.getToken()
    });

    return this._httpClient.get(this.API_URL + '/cart', {headers});
  }

  removeCartItem(cartItemId:any):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer '+this.getToken()
    });

    return this._httpClient.delete(`${this.API_URL}/cart/delete/${cartItemId}`, {headers});
  }

  updateCartQuantity(cartItemId:any, newQuantity:any):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer '+this.getToken()
    });

    const params = new HttpParams().set('quantity', newQuantity);

    return this._httpClient.put(`${this.API_URL}/cart/update/${cartItemId}`,null,{headers, params})
  }

  placeOrder():Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer '+this.getToken()
    });

    return this._httpClient.post(this.API_URL + '/place-order', null, {headers});
  }

  trackOrder(orderId:any):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': 'Bearer '+this.getToken()
    });

    return this._httpClient.get(`${this.API_URL}/order/${orderId}`,{headers});
  }
}
