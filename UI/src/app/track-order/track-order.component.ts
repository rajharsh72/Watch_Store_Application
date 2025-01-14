import { Component } from '@angular/core';
import { UserServiceService } from '../service/user-service.service';

@Component({
  selector: 'app-track-order',
  templateUrl: './track-order.component.html',
  styleUrls: ['./track-order.component.css']
})
export class TrackOrderComponent {

  constructor(private _userService: UserServiceService){}

  orderId:any;
  order:any;
  errMsg: string ='';

  trackOrder() {
    if (!this.orderId) {
      this.errMsg = 'Please enter a valid Order ID.';
      return;
    }

    this._userService.trackOrder(this.orderId).subscribe(
      (orderDetails) => {
        console.log(orderDetails)
        this.order = orderDetails;
        this.errMsg = '';
      },
      (error) => {
        console.log(error);
        this.order = null;
        this.errMsg = 'Unable to find order. Please check the Order ID and try again.';
      }
    );
  }

}
