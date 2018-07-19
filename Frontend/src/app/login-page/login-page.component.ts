import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
  providers: [LoginService],
})
export class LoginPageComponent implements OnInit {


  _email: string;
  _password: string;

  constructor(private _loginService: LoginService) {

  }

  set email (em: string) {
    this._email = em;
  }
  set password (pw: string) {
    this._password = pw;
  }

  get password() {
    return this._password;
  }
  get email() {
    return this._email;
  }


  ngOnInit() {
  }

  clickLogin(): void {
    console.log('we clicked login');
    this._loginService.getLogin(this.email, this.password).subscribe(data => {
      console.log(data);
    });
  }


}
