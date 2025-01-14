import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AdminServiceService } from '../service/admin-service.service';
import { ToastrService } from 'ngx-toastr';
import { timeout } from 'rxjs';

@Component({
  selector: 'app-add-watch',
  templateUrl: './add-watch.component.html',
  styleUrls: ['./add-watch.component.css']
})
export class AddWatchComponent {

  constructor(
    private _adminService: AdminServiceService,
    private _dialogRef: MatDialogRef<AddWatchComponent>,
    private _fb: FormBuilder,
    private _toastr: ToastrService
  ){}

  watchForm =this._fb.group({
    brand: ['', Validators.required],
    name:['', Validators.required],
    type: ['', Validators.required],
    price: ['', Validators.required],
    description: ['', Validators.required],
    quantity: [1, [Validators.required, Validators.min(1)]],
    imageUrl: ['', Validators.required],
  });

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

  addWatch(){
    if(this.watchForm.valid){
      this._adminService.addWatch(this.watchForm.value).subscribe((res)=>{
        this.successToast('Watch added successfully');
        this._dialogRef.close();
      },
      (err)=>{
        this.errorToast('Error Adding Watch');
        this._dialogRef.close();
      });
  }
}

}
