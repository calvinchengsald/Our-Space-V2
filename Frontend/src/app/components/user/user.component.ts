import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { IUser } from '../../interface/iuser';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {



  constructor(private loginService: LoginService) { }

  ngOnInit() {
  }

}
