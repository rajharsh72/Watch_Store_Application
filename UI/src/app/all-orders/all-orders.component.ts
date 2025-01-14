import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { AdminServiceService } from '../service/admin-service.service';

@Component({
  selector: 'app-all-orders',
  templateUrl: './all-orders.component.html',
  styleUrls: ['./all-orders.component.css']
})
export class AllOrdersComponent {

  constructor(
    private _adminService: AdminServiceService,
    private _toastr:ToastrService
  ){}

  orders: any[] =[]
  statusOptions: string[] = ['Placed', 'Processing', 'Shipped', 'Delivered', 'Cancelled'];

  ngOnInit(){
    this.loadOrders();
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

  loadOrders(){
    this._adminService.getAllOrders().subscribe((res) =>{
      this.orders = res;
      console.log(res);
    },
    (err)=>{
      console.log(err);
    })
  }


  updateOrderStatus(orderId:any, newOrderStatus:string){
    this._adminService.updateOrderStatus(orderId, newOrderStatus).subscribe((res) =>{
      this.loadOrders();
      this.successToast("Status Updated");
    }, 
    (err)=>{
      this.errorToast("Error Occured");
    })
  }


}
