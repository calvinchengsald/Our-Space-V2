import { Component, OnInit } from '@angular/core';
import {PostService } from '../services/post.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [PostService],
})
export class HomeComponent implements OnInit {

  constructor(_postService: PostService) { }

  ngOnInit() {
  }

}
