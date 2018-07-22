import { Injectable } from '@angular/core';
import { EnvironmentService } from './environment.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { MessegeModelService } from './messege-model.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  isLoggedIn: boolean; // true if is logged in
  email: string;
  firstName: string;
  lastName: string;
  password = 'dummy pass';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    }),
    withCredentials: true
  };

  constructor(private httpServ: HttpClient, private _MessegeModelService: MessegeModelService,
    private _router: Router) { }

  logout(): Observable<any> {
    // to be implemented
    console.log('in logout from service');
    const url: string = EnvironmentService.APIpath + 'logout.action';
    return this.httpServ.post(url, null, this.httpOptions).pipe(
      map(res => res as string)
    );
  }

  checkLogin() {
    const url: string = EnvironmentService.APIpath + 'checkLogin.action';
    const obs = this.httpServ.post(url, null, this.httpOptions).pipe(
      map(res => res as string));
    obs.subscribe(data => {
      if (data['email'] !== 'null') {
        this._MessegeModelService.error = false;
        this._MessegeModelService.show = false;
        this._MessegeModelService.messege = data['email'];
        this.firstName = data['firstName'];
        this.lastName = data['lastName'];
        this.email = data['email'];
        console.log('logged in1 ');
        this.isLoggedIn = true;
      }
    });
    return obs;
  }

  getLogin(emailz: string, passwordz: string) {
    console.log('in getlogin method with params ' + emailz + '/' + passwordz);
    const url: string = EnvironmentService.APIpath + 'login.action';
    const obj = {
      email: emailz,
      password: passwordz
    };
    const obs = this.httpServ.post(url, obj, this.httpOptions).pipe(
      map(res => res as string)
    );
    obs.subscribe(data => {
      if (!data['password']) {
        this._MessegeModelService.error = true;
        this._MessegeModelService.show = true;
        this._MessegeModelService.messege = data['email'];
        console.log('in pass');
      } else if (data['email']) {
        this._MessegeModelService.error = false;
        this._MessegeModelService.show = false;
        this._MessegeModelService.messege = data['email'];
        this.firstName = data['firstName'];
        this.lastName = data['lastName'];
        this.email = data['email'];
        console.log('logged in ');
        this.isLoggedIn = true;
      }
    });
    return obs;
  }

}
