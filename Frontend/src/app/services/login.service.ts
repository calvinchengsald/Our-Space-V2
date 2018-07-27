import { Injectable } from '@angular/core';
import { EnvironmentService } from './environment.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { MessegeModelService } from './messege-model.service';
import { Router } from '@angular/router';
import { IUser } from '../interface/iuser';
import { METHODS } from 'http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  isLoggedIn: boolean; // true if is logged in
  email: string;
  firstName: string;
  lastName: string;
  password = 'dummy pass';
  allUser: IUser[];
  profileImage: string;

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
    // console.log('in logout from service');
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
      // console.log(data);
      if (!data) {
        this.isLoggedIn = false;
        // this._MessegeModelService.error = false;
        // this._MessegeModelService.show = true;
        // this._MessegeModelService.messege = 'Please Log In';
        // console.log(this._router.url);
        if (this._router.url !== '/login') {

          this._router.navigateByUrl('login');
        }
        return;
      }
      if (data) {
        this._MessegeModelService.error = false;
        this._MessegeModelService.show = false;
        this._MessegeModelService.messege = data['email'];
        this.firstName = data['firstName'];
        this.lastName = data['lastName'];
        this.email = data['email'];
        this.profileImage = data['profilePicture'];
        // console.log('logged in1 ');
        this.isLoggedIn = true;
      }
    });
    return obs;
  }


  getLogin(emailz: string, passwordz: string) {
    // console.log('in getlogin method with params ' + emailz + '/' + passwordz);
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
        // console.log('in pass');
      } else if (data['email']) {
        this._MessegeModelService.error = false;
        this._MessegeModelService.show = false;
        this._MessegeModelService.messege = data['email'];
        this.firstName = data['firstName'];
        this.lastName = data['lastName'];
        this.email = data['email'];
        // console.log('logged in ');
        this.isLoggedIn = true;
      }
    });
    return obs;
  }

  getAllUsers() {
    const url: string = EnvironmentService.APIpath + 'getUserFromAll.action';
    const obs = this.httpServ.post(url, null, this.httpOptions).pipe(
      map(res => res as string));
    obs.subscribe(data => {
      // console.log(data);
      if (data) {
        const userList = [];
        for (let i = 0; i < data.length; i++) {
          const tempUser: IUser = {
            email: data[i]['email'], password: data[i]['password'],
            first_name: data[i]['firstName'], last_name: data[i]['lastName'], profilePicture: data[i]['profilePicture'],
            activated: data[i]['activated']
          };
          userList.push(tempUser);
        }
        this.allUser = userList;
      } else {
        this.allUser = [];
      }
    });
    return obs;
  }
  changePass(emailz: string): Observable<string> {
    console.log('sending reset email');
    const url = EnvironmentService.APIpath + 'reset.action';
    const obj = {
      email: emailz
    };
    return this.httpServ.post(url, obj, this.httpOptions).pipe(
      map(res => res as string)
    );
  }

}
