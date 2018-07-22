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
  isLoggedIn: boolean; // true if is logged in
  _email: string;
  _firstName: string;
  _lastName: string;
  _password = 'dummy pass';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/x-www-form-urlencoded'
    }),
    withCredentials: true
  };

  constructor(private httpServ: HttpClient) { }

  logout(): Observable<any> {
    // to be implemented
    console.log('in logout from service');
    const url: string = EnvironmentService.APIpath + 'logout.action';
    return this.httpServ.post(url, null, this.httpOptions ).pipe(
      map(res => res as string)
    );
  }

  set email(mes: string) {
    this._email = mes;
  }
  get email(): string {
    return this._email;
  }
  set firstName(mes: string) {
    this._firstName = mes;
  }
  get firstName(): string {
    return this._firstName;
  }
  set lastName(mes: string) {
    this._lastName = mes;
  }
  get lastName(): string {
    return this._lastName;
  }
  set password(mes: string) {
    this._password = mes;
  }
  get password(): string {
    return this._password;
  }




  getLogin(emailz: string, passwordz: string): Observable<string> {

    console.log('in getlogin method with params ' + emailz + '/' + passwordz);
    const url: string = EnvironmentService.APIpath + 'login.action';
    const obj = {
      email: emailz,
      password: passwordz
    };
    return this.httpServ.post(url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
  }

}
