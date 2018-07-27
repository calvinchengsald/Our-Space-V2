import { Component, OnInit , Input, AfterContentChecked} from '@angular/core';
import { IPost } from '../../interface/ipost';
import { IUser } from '../../interface/iuser';
import { CommentService } from '../../services/comment.service';
import { LoginService } from '../../services/login.service';
import { MessegeModelService } from '../../services/messege-model.service';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {


  newCommentBody: string;
  likedByUserBool = false;
  likeSrc: string;
  likeList: any;
  @Input() post: IPost;
  constructor(private _commentService: CommentService, private _messegeService: MessegeModelService,
      private _postService: PostService, private _loginService: LoginService ) { }

  likedByUser(post: IPost, email: string): boolean {
    // console.log('called liked By user');
    if (!this.likeList || this.likeList.length === 0) {
      this.likedByUserBool = false;
      return false;
    }

    for (let i = 0; i < this.likeList.length; i++) {
      if (this.likeList[i].email === email) {
        this.likedByUserBool = true;
        return true;
      }
    }
    this.likedByUserBool = false;
    return false;
  }
  ngOnInit() {
    this.likeList = [];
  }

  ngAfterContentChecked() {
    this.likedByUser(this.post, this._loginService.email );
    if  (this.post && this.post.postId !== 0) {
      this.likeList = this._postService.getLikeList(this.post.postId);
      // console.log('like list for comp ' + this.post.body + ' is: ' + this.likeList);
    }
    // console.log(this.post);
  }


  onInsertComment() {
    // console.log(this.newCommentBody);
    if (this.newCommentBody.trim() === '') {
      this._messegeService.show = true;
      this._messegeService.messege = 'This comment must have a body';
      this._messegeService.error = true;
      return;
    }
    this._commentService.insertComment(this.newCommentBody, this.post.postId).subscribe(data => {
      // console.log(data);
      if (data && data['commentId'] !== 0) {
        this._postService.getPost(this.post.postId).subscribe(dataEle => {
          // console.log('data2' + dataEle);
          if (dataEle && dataEle['postId'] !== 0) {
            const l = [];
            // if (dataEle['likedUsers']) {
            //   const likeEle = dataEle['likedUsers'];
            //   for (let li = 0; li < likeEle.length; li++) {
            //     const uu: IUser = {first_name: likeEle[li]['firstName'], last_name: likeEle[li]['lastName'],
            //       email: likeEle[li]['email'],  password: likeEle[li]['password'] , profilePicture: likeEle[li]['profilePicture'] };
            //     l.push(uu);
            //   }
            // }
            const o: IUser = {first_name: dataEle['user']['firstName'], last_name: dataEle['user']['lastName'],
              email: dataEle['user']['email'], password: dataEle['user']['password'], profilePicture: dataEle['user']['profilePicture'],
              activated: dataEle['user']['activated']
               };
            const p: IPost = {postId: dataEle['postId'], body: dataEle['body'], owner: o,
               imageSrc: dataEle['imgSrc'], comments: dataEle['comments'], youtubeLink: dataEle['youtubeLink']
              , created: dataEle['created']};
            this.post = p;
          }
          this.newCommentBody = '';
          // this.homePost = PostService.allPostList;
          // console.log(this.homePost);
        });
      } else {
        this._messegeService.show = true;
        this._messegeService.messege = data['body'];
        this._messegeService.error = true;
      }
      // this.homePost = PostService.allPostList;
      // console.log(this.homePost);
    });
  }

  // deleteLike(): void {
  //   const ele = this.post.likes;
  //   const newLike = [];
  //   for ( let i = 0; i < ele.length; i++) {
  //     if ( ele[i].email === LoginService._email ) {

  //     } else {
  //       newLike.push(ele[i]);
  //     }
  //   }
  //   this.post.likes = newLike;
  //   this.likeSrc = this.likeLinkDefault;

  // }

  clickLike(): void {
    // console.log('i click like');
    if (!this._loginService.isLoggedIn) {
      this._messegeService.show = true;
      this._messegeService.messege = 'Please log in for this action';
      this._messegeService.error = true;
      return;
    }
    // if (this.likedByUser(this.post, LoginService._email) ) {
    //   this.deleteLike();
    // } else {
    //   const u: IUser = {first_name: this._loginService.firstName, last_name: this._loginService.lastName,
    //     email: this._loginService.email, password: this._loginService.password};
    //   console.log(this.post);
    //   console.log(this.post.likes);
    //   this.post.likes.push(u);
    //   this.likeSrc = this.likeLinkLiked;
    // }
    this._postService.updatePost(this.post.postId, this._loginService.email).subscribe(data => {
      // console.log(data);
        this._postService.getPostLikes().subscribe((data3) => {

          // console.log(data3);
          this.likeList = this._postService.getLikeList(this.post.postId);
          this.likedByUser(this.post, this._loginService.email );
          // console.log('value of bool is ' + this.likedByUserBool);
          // console.log(this.likeList);
          setTimeout(function() {
            if (this._postService) {
              this.likeList = this._postService.getLikeList(this.post.postId);
            }
            // this.likedByUser(this.post, this._loginService.email );
          }, 500);
          // this.newCommentBody = this.newCommentBody + '';
        });
    });

  }


}
