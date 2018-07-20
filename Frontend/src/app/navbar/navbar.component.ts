import { Component, OnInit,  AfterContentChecked } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers: [LoginService],
})
export class NavbarComponent implements OnInit {
  title: 'Our Space';
  _name: string;
  isLoggedIn = false;
  get loginService() {
    return this._loginService;
  }

  constructor(private _loginService: LoginService) {

    this.isLoggedIn = LoginService.isLoggedIn;
  }

  set messegeMessege (pw: string) {
    this._name = pw;
  }
  get messegeMessege() {
    return this._name;
  }


  ngOnInit() {
    this.isLoggedIn = LoginService.isLoggedIn;
  }

  ngAfterContentChecked() {
    this.isLoggedIn = LoginService.isLoggedIn;
  }

}
