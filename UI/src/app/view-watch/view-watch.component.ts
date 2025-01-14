import { HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { UserServiceService } from '../service/user-service.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-view-watch',
  templateUrl: './view-watch.component.html',
  styleUrls: ['./view-watch.component.css']
})
export class ViewWatchComponent {

  constructor(
    private _userService: UserServiceService,
    private _router:ActivatedRoute,
    private _toastr:ToastrService
  ){}

  watch:any;
  addToCartForm = new FormGroup({
    'watchId': new FormControl(''),
    'quantity': new FormControl('')
  })

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

  ngOnInit(){
    this._userService.getWatchByWatchId(this._router.snapshot.params['watchId']).subscribe((res: HttpResponse<any>)=>{
      this.watch=res;
    },
    (err)=>{

      if(err.status == 404){
        console.log("watch not found")
      }
    })
  }

  addToCart(watchId:any, quantity:any){
    this.addToCartForm.get('watchId')?.setValue( watchId );
    this.addToCartForm.get('quantity')?.setValue( quantity ?  quantity : 1 );

    if(this.addToCartForm.valid){
      this._userService.addToCart(this.addToCartForm.value).subscribe((res)=>{
        this.successToast("Watch added to cart");
      },
      (err)=>{
        this.errorToast(err.error.errorMessage);
        //console.log(err.error.errorMessage);
      })
    }
  }

}
