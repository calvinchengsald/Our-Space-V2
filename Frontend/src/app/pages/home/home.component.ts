import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { PostService } from '../../services/post.service';
import { Router } from '@angular/router';
import { IPost } from '../../interface/ipost';
import { IUser } from '../../interface/iuser';
import { MessegeModelService } from '../../services/messege-model.service';
import { LoginService } from '../../services/login.service';
import { ProfileService } from '../../services/profile.service';

import { env } from '../../env/env';
import { UploadFileService } from '../../services/upload-file.service';

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
  postBodyImageFile: FileList;
  currDate: Date;
  youtubeLink: string;
  linkTempImage: string;
  uploadTempImage: string;

  get loginService() {
    return this._loginService;
  }
  constructor(private _postService: PostService, private _messegeService: MessegeModelService, private _loginService: LoginService,
    private _uploadService: UploadFileService) {


  }

  ngOnInit() {
    this._loginService.checkLogin().subscribe(data => {
      if (data) {
        console.log('getting all posts');
        this.getAllPost();
      }
    });
  }


  getAllPost(): void {
    // console.log(env.AWSs3access);
    // console.log(env.AWSs3secret);
    this._postService.getAllPost().subscribe(data => {
      console.log(data);
      if (data[0] && data[0]['postId'] !== 0) {

        const postList = [];
        for (let i = 0; i < data.length; i++) {
          const dataEle = data[i];
          // const l = [];
          // if (dataEle['likedUsers']) {
          //   const likeEle = dataEle['likedUsers'];
          //   for (let li = 0; li < likeEle.length; li++) {
          //     const uu: IUser = {
          //       first_name: likeEle[li]['firstName'], last_name: likeEle[li]['lastName'],
          //       email: likeEle[li]['email'], password: likeEle[li]['password'], profilePicture: likeEle[li]['profilePicture']
          //       };
          //     l.push(uu);
          //   }
          // }
          const o: IUser = {
            first_name: dataEle['user']['firstName'], last_name: dataEle['user']['lastName'],
            email: dataEle['user']['email'], password: dataEle['user']['password'], profilePicture: dataEle['user']['profilePicture']
          };
          const p: IPost = {
            postId: dataEle['postId'], body: dataEle['body'], owner: o,
            imageSrc: dataEle['imgSrc'], comments: dataEle['comments'], youtubeLink: dataEle['youtubeLink']
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
    this._postService.getPostLikes();
  }

  selectFile(event) {
    this.postBodyImageFile = event.target.files;
  }

  saveVideoLink(): void {
    const temp = this.youtubeLink.split('=');
    this.newPostYoutube = 'http://youtube.com/embed/' +  temp[1];
    console.log('video link' + this.newPostYoutube.split('='));
  }

  saveImagePost(): void {
    if (this.postBodyImageFile !== undefined) {
      const file = this.postBodyImageFile.item(0);
      this.currDate = new Date();
      this.newPostImage =  this._loginService.email + this.currDate.getMonth() + this.currDate.getDay() + this.currDate.getHours()
                    + this.currDate.getMinutes() + file.name;
    } else {
      this.newPostImage = this.linkTempImage;
    }
    console.log('img = ' + this.newPostImage);
  }

  clickPost(): void {
    console.log('clicked post with ' + this.newPostBody + '/' + this.newPostImage + '/' + this.newPostYoutube);
    this._postService.newPost(this.newPostBody, this.newPostImage, this.newPostYoutube).subscribe(data => {
      console.log(data);
      if (data && data['postId'] !== 0) {
        this.newPostImage = '';
        this.newPostBody = '';
        this.newPostYoutube = '';
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
