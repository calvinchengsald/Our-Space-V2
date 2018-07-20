import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { LoginService } from '../services/login.service';
import { MessegeModelService } from '../services/messege-model.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
  providers: [LoginService],
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
  }

  clickLogin(): void {
    console.log('we clicked login');
    this._loginService.getLogin(this.email, this.password).subscribe(data => {
      console.log(data);
      if (data['error'] ) {
        this._messegeService.error = true;
        this._messegeService.show = true;
        this._messegeService.messege = data['messege'];
      } else if (data['email'] ) {
        this._messegeService.error = false;
        this._messegeService.show = false;
        this._messegeService.messege = data['messege'];
        this._loginService.firstName = data['firstName'];
        this._loginService.lastName = data['lastName'];
        this._loginService.email = data['email'];
        this._loginService.password = data['password'];
        LoginService.isLoggedIn = true;
        this._router.navigateByUrl('/');
      } else {

      }
    });
  }


}
