import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  isLoggedIn:boolean; // true if is logged in 

  constructor() { }

  logout(){
    //to be implemented
  }
}
