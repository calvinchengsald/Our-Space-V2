import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IUser } from '../../interface/iuser';
import { ProfileService } from '../../services/profile.service';
import { LoginService } from '../../services/login.service';
import { PostService } from '../../services/post.service';
import { MessegeModelService } from '../../services/messege-model.service';
import { IPost } from '../../interface/ipost';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  providers: [ProfileService, PostService, MessegeModelService]
})
export class ProfileComponent implements OnInit {

  firstName: string;
  lastName: string;
  email: string;
  showUpdate = false;
  userPost: IPost[];
  password: string;

  // post stuff for user will be in here somewhere or something
  constructor(private _profileService: ProfileService, private _loginService: LoginService, private _postService: PostService,
              private _messegeService: MessegeModelService, private route: ActivatedRoute) {

   }

  get loginService() {
    return this._loginService;
  }

  setValues(user: string) {
    console.log(user);
    if (!user || !user['firstName']) {
      this.firstName = '';
      return;
    }
    this.firstName = user['firstName'];
    console.log(this.firstName);
    this.lastName = user['lastName'];
    this.email = user['email'];

  }

  ngOnInit() {
    console.log('we clicked profile');

    this.email = this.route.snapshot.paramMap.get('email').trim();
    this._profileService.getProfile(this.email).subscribe(data => this.setValues(data));

    this.getAllUserPost(this.email);
    
  }

  clickUpdate(): void {
    console.log('clicked update password');
    this._profileService.postUpdate(this.password).subscribe(
      data => {console.log(data);
    });
  }

  toggleUpdate() {
    this.showUpdate = !this.showUpdate;
  }

  getAllUserPost(userEmail: string): void {
    this._postService.getAllUserPost(userEmail).subscribe(data => {
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
            , created: dataEle['created'] };
          postList.push(p);
        }
        PostService.allPostUser = postList;
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
