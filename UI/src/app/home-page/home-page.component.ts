import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { RegisterUserComponent } from '../register-user/register-user.component';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {

  constructor(
    private _dialog: MatDialog
  ){}
  isLoginView = true;

  toggleView(){
    this.isLoginView = !this.isLoginView;
  }

  opneRegisterationForm(){
    this._dialog.open(RegisterUserComponent);
  }

}
