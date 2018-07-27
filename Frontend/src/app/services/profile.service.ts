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
    }),
    withCredentials: true
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

  getProfile(emailz: string): Observable<string> {

    // console.log('in getprofile method with params ' + emailz );
    this.url = EnvironmentService.APIpath + 'getUser.action';
    const obj = {
      email: emailz,
    };
    return this.httpServ.post<string>(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
  }

  postUpdate(passwordz: string, first_namez: string, last_namez: string): Observable<string> {
    this.url = EnvironmentService.APIpath + 'updateUser.action';

    const obj = {
      password: passwordz,
      first_name: first_namez,
      last_name: last_namez
    };
    return this.httpServ.post(this.url, obj, this.httpOptions).pipe(
      map(res => res as string)
    );
  }

  // send profile picture path to the database
  pictureUpdate(picturez: String): Observable<string> {
    // console.log('uploaded profile picture');
    this.url = EnvironmentService.APIpath + 'profilePicture.action';

    const obj = {
      picture: picturez
    };
    return this.httpServ.post(this.url, obj, this.httpOptions).pipe(
      map(res => res as string)
    );
  }



}
