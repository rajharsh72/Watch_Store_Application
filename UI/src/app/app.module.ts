import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegisterUserComponent } from './register-user/register-user.component';
import { HomePageComponent } from './home-page/home-page.component';
import { MatToolbarModule } from '@angular/material/toolbar' 
import {HttpClientModule} from '@angular/common/http'
import {MatIconModule} from '@angular/material/icon'
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AllWatchesComponent } from './all-watches/all-watches.component';
import {MatCardModule} from '@angular/material/card'
import {MatSelectModule} from '@angular/material/select';
import { ViewWatchComponent } from './view-watch/view-watch.component'
import {MatDividerModule} from '@angular/material/divider'
import {MatDialogModule} from '@angular/material/dialog';
import { AddWatchComponent } from './add-watch/add-watch.component';
import { UpdateWatchComponent } from './update-watch/update-watch.component';
import { StockStatsComponent } from './stock-stats/stock-stats.component';
import { ViewCartComponent } from './view-cart/view-cart.component'
//import {NgChartsModule } from 'ng2-charts';
import {MatListModule} from '@angular/material/list';
import { OrderCheckoutComponent } from './order-checkout/order-checkout.component';
import {MatRadioModule} from '@angular/material/radio';
import { TrackOrderComponent } from './track-order/track-order.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AllOrdersComponent } from './all-orders/all-orders.component'
import {MatTableModule} from '@angular/material/table';
import { ToastrModule } from 'ngx-toastr';
import { CarouselModule } from 'ngx-bootstrap/carousel';
import { FooterComponent } from './footer/footer.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { NgChartsModule } from 'ng2-charts';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserDashboardComponent,
    AdminDashboardComponent,
    RegisterUserComponent,
    HomePageComponent,
    AllWatchesComponent,
    ViewWatchComponent,
    AddWatchComponent,
    UpdateWatchComponent,
    StockStatsComponent,
    ViewCartComponent,
    OrderCheckoutComponent,
    TrackOrderComponent,
    NavbarComponent,
    AllOrdersComponent,
    FooterComponent,
    ForbiddenComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    HttpClientModule,
    MatButtonModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    FormsModule,
    MatIconModule,
    MatCardModule,
    MatSelectModule,
    MatDividerModule,
    MatDialogModule,
    MatListModule,
    MatRadioModule,
    MatTableModule,
    ToastrModule.forRoot(),
    CarouselModule.forRoot(),
    NgChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
