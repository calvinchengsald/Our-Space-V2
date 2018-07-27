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
  likeData: any;


  url: string;
  httpOptions = {
    headers: new HttpHeaders({
    }),
    withCredentials: true
  };

  constructor(private httpServ: HttpClient, private _loginService: LoginService) { }

  getLikeList(postId: number): any {
    // console.log('getting like list of postid: ' + postId);
    const listOfLikes = [];
    for (let i = 0; i < this.likeData.length; i++ ) {
      // console.log(this.likeData[i]);
      // console.log(this.likeData[i]['id']['postId']);
      // console.log(this.likeData[i]['id']['postId'] == postId);
      // console.log(this.likeData[i]['id']['postId'] === postId);
      // console.log('comparinging inside: ' + this.likeData[i]['id']['postId']+'/'+postId +':' +(this.likeData[i]['postId'] == postId) );
      if (this.likeData[i]['id']['postId'] === postId) {
        listOfLikes.push(this.likeData[i]['id']);
      }
    }
    return listOfLikes;
  }


  getAllPost(): Observable<string> {


    this.url = EnvironmentService.APIpath + 'getPostFromAll.action';
    const obj = {

    };
    const resp = this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
    this.getPostLikes();
    return resp;
  }

  getAllUserPost(em: string): Observable<string> {

    this.url = EnvironmentService.APIpath + 'getPostFromUser.action';
    const obj = {
      email: em
    };
    const resp = this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
    this.getPostLikes();
    return resp;
  }

  getPost(id: number): Observable<string> {

    this.url = EnvironmentService.APIpath + 'getPost.action';
    const obj = {
      postId: id
    };
    const resp = this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
    this.getPostLikes();
    return resp;
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
    const resp = this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
    this.getPostLikes();
    return resp;
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
    const resp = this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );

    return resp;
  }

  getPostLikes(): Observable<string> {
    this.url = EnvironmentService.APIpath + 'getPostLikes.action';
    const obj = {    };

    const result = this.httpServ.post(this.url, obj, this.httpOptions ).pipe(
      map(res => res as string)
    );
    result.subscribe(data => {
      this.likeData = data;
      // console.log(data);
    });
    return result;
  }
}
