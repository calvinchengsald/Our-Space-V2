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
  static _email: string;
  static _firstName: string;
  static _lastName: string;
  static _password = 'dummy pass';

  url: string;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
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

  set email(mes: string) {
    LoginService._email = mes;
  }
  get email(): string {
    return LoginService._email;
  }
  set firstName(mes: string) {
    LoginService._firstName = mes;
  }
  get firstName(): string {
    return LoginService._firstName;
  }
  set lastName(mes: string) {
    LoginService._lastName = mes;
  }
  get lastName(): string {
    return LoginService._lastName;
  }
  set password(mes: string) {
    LoginService._password = mes;
  }
  get password(): string {
    return LoginService._password;
  }




  getLogin(emailz: string, passwordz: string): Observable<string> {

    console.log('in getlogin method with params ' + emailz + '/' + passwordz);
    this.url = EnvironmentService.APIpath + 'login.action';
    const obj = {
      email: emailz,
      password: passwordz
    };
    return this.httpServ.post(this.url, obj, this.httpOptions).pipe(
      map(res => res as string)
    );
  }

  changePass(emailz: string): Observable<string> {
    console.log('sending reset email');
    this.url = EnvironmentService.APIpath + 'reset.action';
    const obj = {
      email: emailz
    };
    return this.httpServ.post(this.url, obj, this.httpOptions).pipe(
      map(res => res as string)
    );
  }

}
