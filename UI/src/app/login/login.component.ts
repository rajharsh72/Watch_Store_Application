import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginServiceService } from '../service/login-service.service';
import { UserServiceService } from '../service/user-service.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(
    private _loginService: LoginServiceService,
    private _router:Router,
    private _userService:UserServiceService,
    private _toastr: ToastrService
  ){}

  loginForm = new FormGroup({
    'username': new FormControl('', Validators.required),
    'userPassword': new FormControl('', Validators.required)
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

  login(){
    if(this.loginForm.valid){
      this._loginService.login(this.loginForm.value).subscribe((res)=>{
        this._userService.setCurrentUser(res.user.name);
        this._userService.setToken(res.jwtToken);
        // console.log(res.user.address);
        this._userService.setUserAddress(res.user.address);
        const role = res.user.roles[0]["name"];

        if(role ==='USER' && this._userService.getCurrentUser != null){
          this._userService.setCurrentRole(res.user.roles[0]["name"]);
          this._router.navigate(['user-dashboard']);
          this.successToast("Welcome back "+ this._userService.getCurrentUser());
          
        }else{
          this._userService.setCurrentRole(res.user.roles[0]["name"]);
          this._router.navigate(['admin-dashboard']);
          this.successToast("Welcome back "+ this._userService.getCurrentUser());

        }
      },
      (err)=>{
        this.errorToast("Invalid Credentials!! Try Again");
        console.log(err);
      });
    }
  }
}
