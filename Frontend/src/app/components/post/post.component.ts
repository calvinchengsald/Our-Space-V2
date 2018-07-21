import { Component, OnInit , Input} from '@angular/core';
import { IPost } from '../../interface/ipost';
import { IUser } from '../../interface/iuser';
import { CommentService } from '../../services/comment.service';
import { MessegeModelService } from '../../services/messege-model.service';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {


  newCommentBody: string;
  @Input() post: IPost;
  constructor(private _commentService: CommentService, private _messegeService: MessegeModelService, private _postService: PostService ) { }

  ngOnInit() {
  }

  onInsertComment() {
    this._commentService.insertComment(this.newCommentBody, this.post.postId).subscribe(data => {
      console.log(data);
      if (data && data['commentId'] !== 0) {
        this._postService.getPost(this.post.postId).subscribe(dataEle => {
          console.log('data2' + dataEle);
          if (dataEle && dataEle['postId'] !== 0) {
            const o: IUser = {first_name: dataEle['user']['firstName'], last_name: dataEle['user']['lastName'],
              email: dataEle['user']['email'],  };
            const p: IPost = {postId: dataEle['postId'], body: dataEle['body'], owner: o,
              likes: dataEle['likes'], imageSrc: dataEle['imgSrc'], comments: dataEle['comments'], youtubeLink: dataEle['youtubeLink'] };
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


}
