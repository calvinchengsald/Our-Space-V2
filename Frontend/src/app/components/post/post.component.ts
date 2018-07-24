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
  likeLinkDefault = 'https://cms.jotform.com/uploads/help/document/joey/88_facebook_like_button_big.jpeg';
  likeLinkLiked = 'https://s3.amazonaws.com/our-space/UI/btn-liked.jpeg';
  likeSrc = this.likeLinkDefault;
  @Input() post: IPost;
  constructor(private _commentService: CommentService, private _messegeService: MessegeModelService,
      private _postService: PostService, private _loginService: LoginService ) { }

  likedByUser(post: IPost, email: string): boolean {
    if (!post.likes) {return false; }
    const likes = post.likes;
    for (let i = 0; i < likes.length; i++) {
      if (likes[i].email === email) {
        return true;
      }
    }
    return false;
  }
  ngOnInit() {  }

  ngAfterContentChecked() {
    if (this.likedByUser(this.post, this._loginService.email )) {
      this.likeSrc = this.likeLinkLiked;
    } else {
      this.likeSrc = this.likeLinkDefault;
    }
  }


  onInsertComment() {
    this._commentService.insertComment(this.newCommentBody, this.post.postId).subscribe(data => {
      console.log(data);
      if (data && data['commentId'] !== 0) {
        this._postService.getPost(this.post.postId).subscribe(dataEle => {
          console.log('data2' + dataEle);
          if (dataEle && dataEle['postId'] !== 0) {
            const l = [];
            if (dataEle['likedUsers']) {
              const likeEle = dataEle['likedUsers'];
              for (let li = 0; li < likeEle.length; li++) {
                const uu: IUser = {first_name: likeEle[li]['firstName'], last_name: likeEle[li]['lastName'],
                  email: likeEle[li]['email'],  password: likeEle[li]['password'] , profilePicture: likeEle[li]['profilePicture'] };
                l.push(uu);
              }
            }
            const o: IUser = {first_name: dataEle['user']['firstName'], last_name: dataEle['user']['lastName'],
              email: dataEle['user']['email'], password: dataEle['user']['password'], profilePicture: dataEle['user']['profilePicture']
               };
            const p: IPost = {postId: dataEle['postId'], body: dataEle['body'], owner: o,
              likes: l, imageSrc: dataEle['imgSrc'], comments: dataEle['comments'], youtubeLink: dataEle['youtubeLink']
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
    console.log('i click like');
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
      console.log(data);
      if (data && data['postId'] !== 0) {
        const l = [];
        if (data['likedUsers']) {
          const likeEle = data['likedUsers'];
          for (let li = 0; li < likeEle.length; li++) {
            const uu: IUser = {first_name: likeEle[li]['firstName'], last_name: likeEle[li]['lastName'],
              email: likeEle[li]['email'],  password: likeEle[li]['password'], profilePicture: likeEle[li]['profilePicture'] };
            l.push(uu);
          }
        }
        const o: IUser = {first_name: data['user']['firstName'], last_name: data['user']['lastName'],
          email: data['user']['email'], password: data['user']['password'], profilePicture: data['user']['profilePicture'] };
        const p: IPost = {postId: data['postId'], body: data['body'], owner: o,
          likes: l, imageSrc: data['imgSrc'], comments: data['comments'], youtubeLink: data['youtubeLink']
          , created: data['created'] };
        this.post = p;
      } else {
        this._messegeService.show = true;
        this._messegeService.messege = data['body'];
        this._messegeService.error = true;
      }
    });

  }


}
