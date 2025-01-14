import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AllOrdersComponent } from './all-orders/all-orders.component';
import { AllWatchesComponent } from './all-watches/all-watches.component';
import { adminGuard } from './auth-guard/admin.guard';
import { authGuard } from './auth-guard/auth.guard';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomePageComponent } from './home-page/home-page.component';
import { OrderCheckoutComponent } from './order-checkout/order-checkout.component';
import { StockStatsComponent } from './stock-stats/stock-stats.component';
import { TrackOrderComponent } from './track-order/track-order.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { ViewCartComponent } from './view-cart/view-cart.component';
import { ViewWatchComponent } from './view-watch/view-watch.component';

const routes: Routes = [
  {path: '', component: HomePageComponent, pathMatch: 'full'},
  {path: 'home', component: HomePageComponent, pathMatch: 'full'},
  {path: 'all-watches', component:AllWatchesComponent, pathMatch: 'full'},
  {path: 'watch/:watchId', component:ViewWatchComponent},
  {path: 'user-dashboard', component: UserDashboardComponent, pathMatch:'full', canActivate: [authGuard]},
  {path: 'admin-dashboard', component: AdminDashboardComponent, pathMatch: 'full', canActivate: [adminGuard]},
  {path: 'stock-stats', component: StockStatsComponent, pathMatch: 'full', canActivate: [adminGuard]},
  {path: 'view-cart', component:ViewCartComponent, pathMatch: 'full'},
  {path: 'order-checkout', component:OrderCheckoutComponent, pathMatch:'full'},
  {path: 'track-order', component: TrackOrderComponent, pathMatch:'full'},
  {path:'orders', component:AllOrdersComponent, pathMatch:'full',canActivate: [adminGuard]},
  {path:'forbidden', component:ForbiddenComponent, pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
