import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { UserServiceService } from '../service/user-service.service';

@Component({
  selector: 'app-order-checkout',
  templateUrl: './order-checkout.component.html',
  styleUrls: ['./order-checkout.component.css']
})
export class OrderCheckoutComponent {

  constructor(
    private _userService: UserServiceService,
    private _toastr: ToastrService
  ){}

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



  cartItems: any[] = [];
  totalPrice: number =0.0;
  selectedAddress:any;
  paymentMode: string ='cod';
  orderPlaced = false;
  orderId: any;

  ngOnInit(){
    this.selectedAddress = JSON.parse(this._userService.getUserAddress());
    this.loadCartDetails();
  }

  loadCartDetails():void{
    this._userService.getUserCart().subscribe((res) =>{
      this.cartItems = res.cartItems;
      this.totalPrice = res.totalPrice;

      //console.log(this.cartItems, this.totalPrice);
    },
    (err)=>{
      console.log(err);
    })
  }

  placeOrder(){
    this._userService.placeOrder().subscribe((res)=>{
      console.log(res.orderId);
      this.orderId = res.orderId;
      this.orderPlaced = true;
      this.successToast("Order has been places successfully");
    },
    (err)=>{
      this.errorToast(err.error.errorMessage)
    });
  }

}
