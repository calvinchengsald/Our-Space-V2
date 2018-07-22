import { Injectable } from '@angular/core';
import { EnvironmentService } from './environment.service';
import { LoginService } from './login.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  url: string;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/x-www-form-urlencoded'
    })
  };

  constructor(private httpServ: HttpClient, private _loginService: LoginService) { }


  insertComment(bodyz: string, postIdz: number): Observable<string> {

    console.log('in insert new comment ' + bodyz + '/' + postIdz);
    this.url = EnvironmentService.APIpath + 'insertComment.action';
    const obj = {
      postId: postIdz,
      body: bodyz,
      email: this._loginService.isLoggedIn ? this._loginService.email : '',
    };
    return this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
  }
}
