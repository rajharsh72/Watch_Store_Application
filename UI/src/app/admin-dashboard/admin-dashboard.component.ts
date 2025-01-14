import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddWatchComponent } from '../add-watch/add-watch.component';
import { UserServiceService } from '../service/user-service.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {

  constructor(
    private _dialog: MatDialog,
    private _userService:UserServiceService
  ){}

  openAddDialog(){
    this._dialog.open(AddWatchComponent);
  }

  currentRole = this._userService.getCurrentRole();

}
