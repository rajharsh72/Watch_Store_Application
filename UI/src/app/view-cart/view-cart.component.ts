import { Component } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { ToastrService } from 'ngx-toastr';
import { UserServiceService } from '../service/user-service.service';

@Component({
  selector: 'app-view-cart',
  templateUrl: './view-cart.component.html',
  styleUrls: ['./view-cart.component.css']
})
export class ViewCartComponent {
event: any;
$(arg0: any): number {
throw new Error('Method not implemented.');
}

  constructor(
    private _userService: UserServiceService,
    private _toastr:ToastrService
  ){}

  cartItems: any[] = [];
  totalPrice: number =0.0;

  ngOnInit(){
    this.loadCartDetails();
  }


  successToast(msg:string){
    this._toastr.success(msg,'',{
      timeOut: 2000,
    });
  }

  errorToast(msg:string){
    this._toastr.error(msg,'',{
      timeOut:2000,
    });
  }

  loadCartDetails():void{
    this._userService.getUserCart().subscribe((res) =>{
      this.cartItems = res.cartItems;
      this.totalPrice = res.totalPrice;
    },
    (err)=>{
      console.log(err);
    })
  }

  removeCartItem(cartItemId:any){
    this._userService.removeCartItem(cartItemId).subscribe((res)=>{
      this.successToast("Item removed");
      this.loadCartDetails();
    },
    (err) =>{
      this.errorToast("Error removing item");
      //console.log(err);
    })
  }

  upadateCartQuantity(cartItemId:any, newQuantity:number){
    if(newQuantity>0){
      console.log(newQuantity);
      this._userService.updateCartQuantity(cartItemId, newQuantity).subscribe((res)=>{
        this.loadCartDetails();
        this.successToast("Quantity updated");
        //console.log(res);
      },(err)=>{
        this.errorToast(err.error.errorMessage);
        //console.log(err);
      })
    }
  }

}
