import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IUser } from '../interface/iuser';
import { ProfileService } from '../services/profile.service';
import { LoginService } from '../services/login.service';
import { PostService } from '../services/post.service';
import { MessegeModelService } from '../services/messege-model.service';
import { IPost } from '../interface/ipost';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  providers: [ProfileService, PostService, MessegeModelService]
})
export class ProfileComponent implements OnInit {

  _firstName: string;
  _lastName: string;
  _email: string;
  showUpdate = false;
  userPost: IPost[];

  // post stuff for user will be in here somewhere or something
  constructor(private _profileService: ProfileService, private _loginService: LoginService, private _postService: PostService,
              private _messegeService: MessegeModelService) {

   }

  set firstName (fn: string) {
    this._firstName = fn;
  }

  set lastName (ln: string) {
    this._lastName = ln;
  }

  set email (em: string) {
    this._email = em;
  }

  get firstName () {
    console.log('in first name');
    console.log('Name = ' + this._profileService.firstName );
    return this._profileService.firstName;
  }

  get lastName () {
    return this._lastName;
  }

  get email () {
    return this._email;
  }


  setValues(user: string) {
    this._firstName = user['firstName'];
    console.log(this._firstName);
    this._lastName = user['lastName'];
    this._email = user['email'];

  }

  ngOnInit() {
    console.log('we clicked profile');
    this._profileService.getProfile(this._loginService.firstName, this._loginService.lastName,
      this._loginService.email).subscribe(data => this.setValues(data));

    this.getAllUserPost();
  }

  clickProfile(): void {

  }

  toggleUpdate() {
    this.showUpdate = !this.showUpdate;
  }

  getAllUserPost(): void {
    this._postService.getAllUserPost(this._loginService.email).subscribe(data => {
      console.log(data);
      if (data[0] && data[0]['postId'] !== 0) {

        const postListUser = [];
        for (let i = 0; i < data.length; i++) {
          const dataEle = data[i];
          const o: IUser = {first_name: dataEle['user']['firstName'], last_name: dataEle['user']['lastName'],
              email: dataEle['user']['email'],  };
          const p: IPost = {postId: dataEle['postId'], body: dataEle['body'], owner: o,
            likes: dataEle['likes'], imageSrc: dataEle['imgSrc'], comments: dataEle['comments'], youtubeLink: dataEle['youtubeLink'] };
          postListUser.push(p);
        }
        PostService.allPostUser = postListUser;
      } else {
        this._messegeService.show = true;
        this._messegeService.messege = data['body'];
        this._messegeService.error = false;
        PostService.allPostUser = [];
      }
      this.userPost = PostService.allPostUser;
      console.log(this.userPost);
    });
  }

}
