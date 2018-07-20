import { Component, OnInit } from '@angular/core';
import { MessegeModelService } from '../services/messege-model.service';

@Component({
  selector: 'app-messege',
  templateUrl: './messege.component.html',
  styleUrls: ['./messege.component.css']
})
export class MessegeComponent implements OnInit {



  _messegeMessege: string;


  constructor(private _messegeService: MessegeModelService) {

  }

  ngOnInit() {
  }

  get messegeService() {
    return this._messegeService;
  }

  set messegeMessege (pw: string) {
    this._messegeMessege = pw;
  }

  get messegeMessege() {
    return this._messegeMessege;
  }

  ngAfterContentChecked() {
    this.messegeMessege = this._messegeService.messege;
  }


  clickClose(): void {
    this._messegeService.show = false;
  }

}
