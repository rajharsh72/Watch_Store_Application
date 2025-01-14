import { Component } from '@angular/core';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent {

  images = [
    'https://picsum.photos/900/500?random&t=1',
    'https://picsum.photos/900/500?random&t=2',
    'https://picsum.photos/900/500?random&t=3'
  ];


}
