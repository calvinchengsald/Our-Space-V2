import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IUser } from '../../interface/iuser';
import { ProfileService } from '../../services/profile.service';
import { LoginService } from '../../services/login.service';
import { PostService } from '../../services/post.service';
import { MessegeModelService } from '../../services/messege-model.service';
import { IPost } from '../../interface/ipost';
import { UploadFileService } from '../../services/upload-file.service';

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
  imgSrc: string;
  selectedFiles: FileList;
  currDate: Date;
  filename: string;
  updatable: boolean;


  // post stuff for user will be in here somewhere or something
  constructor(private _profileService: ProfileService, private _loginService: LoginService, private _postService: PostService,
              private _messegeService: MessegeModelService, private _uploadService: UploadFileService, private route: ActivatedRoute) {

   }

   // upload profile picture
   uploadImage() {
     const file = this.selectedFiles.item(0);
     this.currDate = new Date();
     this.filename = this.email + this.currDate.getMonth() + this.currDate.getDay() + this.currDate.getHours()
                  + this.currDate.getMinutes() + file.name;
      console.log('filename = ' + this.filename);
     console.log(this._uploadService.uploadProfilePicture(file, this.filename,
      data => {this._profileService.getProfile(this.email).subscribe(data2 => this.setValues(data2)); }));
    //  this.imgSrc = this._uploadService.BUCKET_URL + this._uploadService.PROFILE_FOLDER + this.filename;
     this._profileService.pictureUpdate(this._uploadService.BUCKET_URL + this._uploadService.PROFILE_FOLDER + this.filename)
     .subscribe(data => {
      //  console.log('pic resp = ' + data);
      //  console.log(data);
      //  console.log(data['Location']);
      //  this.selectedFiles =
      
      });
    //  this.imgSrc = this.filename;
   }


   get loginService() {
     return this._loginService;
   }

   selectFile(event) {
    this.selectedFiles = event.target.files;
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
    this.password = user['password'];
    this.imgSrc = user['profilePicture'];
    console.log('img src:' + this.imgSrc);
    if (this.email === this._loginService.email) {
      this.updatable = true;
    } else {
      this.updatable = false;
    }
  }

  ngOnInit() {
    console.log('we clicked profile');
    this._loginService.checkLogin();
    this.email = this.route.snapshot.paramMap.get('email').trim();
    this._profileService.getProfile(this.email).subscribe(data => this.setValues(data));

    this.getAllUserPost(this.email);
  }

  clickUpdate(): void {
    console.log('clicked update password');
    this._profileService.postUpdate(this.password, this.firstName, this.lastName).subscribe(
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
          // const l = [];
          // if (dataEle['likedUsers']) {
          //   const likeEle = dataEle['likedUsers'];
          //   for (let li = 0; li < likeEle.length; li++) {
          //     const uu: IUser = {
          //       first_name: likeEle[li]['firstName'], last_name: likeEle[li]['lastName'],
          //       email: likeEle[li]['email'], password: likeEle[li]['password'], profilePicture: likeEle[li]['profilePicture']
          //     };
          //     l.push(uu);
          //   }
          // }
          const o: IUser = {
            first_name: dataEle['user']['firstName'], last_name: dataEle['user']['lastName'],
            email: dataEle['user']['email'], password: dataEle['user']['password'], profilePicture: dataEle['user']['profilePicture'],
            activated: dataEle['user']['activated']
          };
          const p: IPost = {
            postId: dataEle['postId'], body: dataEle['body'], owner: o,
             imageSrc: dataEle['imgSrc'], comments: dataEle['comments'], youtubeLink: dataEle['youtubeLink'],
            created: dataEle['Created'],
            };
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
