import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  static isLoggedIn = false; // true if is logged in

  constructor() { }

  logout() {
    // to be implemented
    console.log('in logout from service');
    LoginService.isLoggedIn = false;
    console.log(LoginService.isLoggedIn);
  }

  login(): void {
    console.log('in login from service');
    LoginService.isLoggedIn = true;
    console.log(LoginService.isLoggedIn);
  }

}
