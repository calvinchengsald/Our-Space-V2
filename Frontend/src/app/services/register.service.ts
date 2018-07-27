import { Injectable } from '@angular/core';
import { EnvironmentService } from './environment.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  url: string;
  // body: FormData;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    }),
    withCredentials: true
  };

  constructor(private httpServ: HttpClient) { }

  postRegister(usernamez: string, passwordz: string, con_passwordz: string, first_namez: string, last_namez: string): Observable<string> {

    // console.log('in postRegister method with params ' + usernamez + ',' + passwordz + ',' + first_namez + ',' + last_namez);
    if (passwordz === con_passwordz) {
      // console.log('passwords match');
      this.url = EnvironmentService.APIpath + 'register.action';
      const obj = {
        username: usernamez,
        password: passwordz,
        first_name: first_namez,
        last_name: last_namez
      };
      return this.httpServ.post(this.url, obj, this.httpOptions).pipe(
        map(res => res as string)
      );
    } else {
      // console.log('Passwords do not match');
    }
  }
}
