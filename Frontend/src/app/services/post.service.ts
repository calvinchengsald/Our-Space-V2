import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { EnvironmentService } from './environment.service';
import { HttpHeaders } from '@angular/common/http';
import {IPost} from '../interface/ipost';
import {IUser} from '../interface/iuser';
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
    })
  };

  constructor(private httpServ: HttpClient) { }



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
      email: LoginService.isLoggedIn ? LoginService._email : '',
    };
    return this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
  }

  updatePost(postIdz: number, emailz: string): Observable<string> {
    this.url = EnvironmentService.APIpath + 'updatePostLikes.action';
    // const likesObjList = [];
    // for (let i = 0; i < likes.length; i++) {
    //   const likesObj = {
    //     email: likes[i].email,
    //     password: likes[i].password,
    //     first_name: likes[i].first_name,
    //     last_name: likes[i].last_name,
    //   };
    //   likesObjList.push(likesObj);
    // }
    const obj = {
      postId: postIdz,
      email: emailz,
    };

    return this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
  }
}
