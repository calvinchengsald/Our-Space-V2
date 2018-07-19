import { Injectable } from '@angular/core';
import { EnvironmentService } from './environment.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  static isLoggedIn = false; // true if is logged in
  url: string;
  httpOptions = {
    headers: new HttpHeaders({
    })
  };

  constructor(private httpServ: HttpClient) { }

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




  getLogin(emailz: string, passwordz: string): Observable<string> {

    console.log('in getlogin method with params ' + emailz + '/' + passwordz);
    this.url = EnvironmentService.APIpath + 'login.action';
    const obj = {
      email: emailz,
      password: passwordz
    };
    return this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
  }

}
