import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterUserServiceService } from '../service/register-user-service.service';
import {ToastrService} from 'ngx-toastr';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent {
  constructor(
    private _registerService: RegisterUserServiceService,
    private fb:FormBuilder,
    private _toastr:ToastrService,
    private _dialogRef: MatDialogRef<RegisterUserComponent>
  ){}

  registrationForm = this.fb.group({
    username:['',[Validators.required, Validators.email]],
    password:['',[Validators.required, Validators.minLength(8)]],
    name:['', Validators.required],
    mobileNumber:['', [Validators.required, Validators.pattern("^[0-9]{10}$")]],
    address: this.fb.group({
      street: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      zipCode: ['', [Validators.required, Validators.pattern("^[0-9]{6}$")]],
      country: ['', Validators.required]
    })
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


  registerUser(){
    if(this.registrationForm.valid){
      this._registerService.registerUser(this.registrationForm.value).subscribe((res)=>{
        this.successToast('User Registered Successfully');
        this._dialogRef.close();
        this.registrationForm.reset();
      },
      (err)=>{
        this.errorToast('User Registration Failed');
      });
    }
    else{
      Object.values(this.registrationForm.controls).forEach(control =>{
        if(control instanceof FormGroup){
          Object.values(control.controls).forEach(c => c.markAsTouched());
        }else{
          control.markAsTouched();
        }
      });
    }
  }
}
