import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { PostService } from '../../services/post.service';
import { Router } from '@angular/router';
import { IPost } from '../../interface/ipost';
import { IUser } from '../../interface/iuser';
import { MessegeModelService } from '../../services/messege-model.service';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {


  newPostImage: string;
  newPostBody: string;
  newPostYoutube: string;
  homePost: IPost[];
  isLoggedIn = false;
  get loginService() {
    return this._loginService;
  }
  constructor(private _postService: PostService, private _messegeService: MessegeModelService, private _loginService: LoginService) {


  }

  ngOnInit() {
    this._loginService.checkLogin().subscribe(data => {
      if (data) {
        this.getAllPost();
      }
    });
    this._loginService.getAllUsers();
  }


  getAllPost(): void {
    this._postService.getAllPost().subscribe(data => {
      console.log(data);
      if (data[0] && data[0]['postId'] !== 0) {

        const postList = [];
        for (let i = 0; i < data.length; i++) {
          const dataEle = data[i];
          const l = [];
          if (dataEle['likes']) {
            const likeEle = dataEle['likes'];
            for (let li = 0; li < likeEle.length; li++) {
              const uu: IUser = {
                first_name: likeEle[li]['firstName'], last_name: likeEle[li]['lastName'],
                email: likeEle[li]['email'], password: likeEle[li]['password'], profilePicture: likeEle[li]['profilePicture']
                };
              l.push(uu);
            }
          }
          const o: IUser = {
            first_name: dataEle['user']['firstName'], last_name: dataEle['user']['lastName'],
            email: dataEle['user']['email'], password: dataEle['user']['password'], profilePicture: dataEle['user']['profilePicture']
            };
          const p: IPost = {
            postId: dataEle['postId'], body: dataEle['body'], owner: o,
            likes: l, imageSrc: dataEle['imgSrc'], comments: dataEle['comments'], youtubeLink: dataEle['youtubeLink']
            , created: dataEle['created']
            };
          postList.push(p);
        }
        PostService.allPostList = postList;
      } else {
        this._messegeService.show = true;
        this._messegeService.messege = data['body'];
        this._messegeService.error = false;
        PostService.allPostList = [];
      }
      this.homePost = PostService.allPostList;
      console.log(this.homePost);
    });
  }

  clickPost(): void {
    console.log('clicked post with ' + this.newPostBody + '/' + this.newPostImage + '/' + this.newPostYoutube);
    this._postService.newPost(this.newPostBody, this.newPostImage, this.newPostYoutube).subscribe(data => {
      console.log(data);
      if (data && data['postId'] !== 0) {
        this.getAllPost();
      } else {
        this._messegeService.show = true;
        this._messegeService.messege = data['body'];
        this._messegeService.error = false;
        PostService.allPostList = [];
      }
      // this.homePost = PostService.allPostList;
      // console.log(this.homePost);
    });
  }


}
