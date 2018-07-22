import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { EnvironmentService } from './environment.service';
import { HttpHeaders } from '@angular/common/http';
import {IPost} from '../interface/ipost';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  static allPostList: IPost[];
  static allPostUser: IPost[];
  static currentPost: IPost;


  url: string;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/x-www-form-urlencoded'
    })
  };

  constructor(private httpServ: HttpClient, private _loginService: LoginService) { }



  getAllPost(): Observable<string> {

    this.url = EnvironmentService.APIpath + 'getPostFromAll.action';
    const obj = {

    };
    return this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
  }

  getPost(id: number): Observable<string> {

    this.url = EnvironmentService.APIpath + 'getPost.action';
    const obj = {
      postId: id
    };
    return this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
  }

  newPost(bodyz: string, image: string, youtube: string): Observable<string> {
    console.log('in new post with ' + bodyz + ' / ' + image + ' / ' + youtube);
    this.url = EnvironmentService.APIpath + 'insertPost.action';
    const obj = {
      body: bodyz,
      imgsrc: image,
      youtubelink: youtube,
      email: this._loginService.isLoggedIn ? this._loginService.email : '',
    };
    return this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
  }
}
