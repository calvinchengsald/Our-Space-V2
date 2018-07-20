import { Component, OnInit,  AfterContentChecked } from '@angular/core';
import { LoginService } from '../services/login.service';
import { ProfileService } from '../services/profile.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers: [LoginService, ProfileService],
})
export class NavbarComponent implements OnInit {
  title: 'Our Space';

  isLoggedIn = false;
  get loginService() {
    return this._loginService;
  }

  constructor(private _loginService: LoginService) {

    this.isLoggedIn = LoginService.isLoggedIn;
  }

  ngOnInit() {
    this.isLoggedIn = LoginService.isLoggedIn;
  }

  ngAfterContentChecked() {
    this.isLoggedIn = LoginService.isLoggedIn;
  }

}
