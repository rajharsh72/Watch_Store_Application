import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddWatchComponent } from '../add-watch/add-watch.component';
import { UserServiceService } from '../service/user-service.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  currentRole: string ='';

  constructor(
    private _userService: UserServiceService,
    private _dialog:MatDialog,
    private _toastr: ToastrService
  ){}

  successToast(msg:string){
    this._toastr.success(msg,'',{
      timeOut: 2000,
    });
  }

  ngOnInit(){
    this.currentRole = this._userService.getCurrentRole();
  }

  openAddDialog(){
    this._dialog.open(AddWatchComponent);
  }

  logout(){
    this.successToast("Logged out successfully")
    localStorage.clear();
  }

  

}
