import { Component, OnInit } from '@angular/core';
import { MessegeModelService } from '../../services/messege-model.service';

@Component({
  selector: 'app-messege',
  templateUrl: './messege.component.html',
  styleUrls: ['./messege.component.css']
})
export class MessegeComponent implements OnInit {


  constructor(private _messegeService: MessegeModelService) {

  }

  ngOnInit() {
  }

  get messegeService() {
    return this._messegeService;
  }


  clickClose(): void {
    this._messegeService.show = false;
  }

}
