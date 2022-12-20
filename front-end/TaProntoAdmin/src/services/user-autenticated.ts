import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable, of} from "rxjs";
import {AuthService} from "../app/core/services/auth.service";

@Injectable()
export class UserAutenticated implements CanActivate {
  constructor(
    private readonly authService: AuthService,
    private readonly router: Router
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
    Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let authenticated = !!this.authService.getAccessTokenInStorage();

    if(!authenticated){
      this.router.navigate(['login']).then();
    }

    return of(authenticated);
  }
}
