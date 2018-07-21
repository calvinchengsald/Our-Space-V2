import { Injectable } from '@angular/core';
import { EnvironmentService } from './environment.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { IUser } from '../interface/iuser';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  static _email: string;
  static _firstName: string;
  static _lastName: string;

  url: string;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/x-www-form-urlencoded'
    })
  };

  constructor(private httpServ: HttpClient) { }

  set firstName (fn: string) {
    ProfileService._firstName = fn;
  }

  set lastName (ln: string) {
    ProfileService._lastName = ln;
  }

  set email (em: string) {
    ProfileService._email = em;
  }

  get firstName () {
    return ProfileService._firstName;
  }

  get lastName () {
    return ProfileService._lastName;
  }

  get email () {
    return ProfileService._email;
  }

  getProfile(first_namez: string, last_namez: string, emailz: string): Observable<string> {

    console.log('in getprofile method with params ' + emailz + '/' + first_namez + '/' + last_namez);
    this.url = EnvironmentService.APIpath + 'getUser.action';
    const obj = {
      username: emailz,
      firstName: first_namez,
      lastName: last_namez
    };
    return this.httpServ.post<string>(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
  }
}
