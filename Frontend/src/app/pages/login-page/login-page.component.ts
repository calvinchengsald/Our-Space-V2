import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { MessegeModelService } from '../../services/messege-model.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent implements OnInit {


  _email: string;
  _password: string;

  constructor(private _loginService: LoginService, private _messegeService: MessegeModelService, private _router: Router ) {

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
    this._loginService.checkLogin();
  }

  clickLogin(): void {
    console.log('we clicked login');
    this._loginService.getLogin(this.email, this.password).subscribe( res => {
      this._router.navigateByUrl('/');
    });
  }


}
