import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { UserServiceService } from '../service/user-service.service';

export const authGuard: CanActivateFn = (route, state) => {

  const userService = inject(UserServiceService);
  const router = inject(Router);

  if(userService.getToken() && userService.getCurrentRole() === 'USER'){
    return true;
  }
  else{
    localStorage.clear();
    return router.createUrlTree(['/forbidden'], {queryParams: {returnUrl: state.url}});
  }
};
