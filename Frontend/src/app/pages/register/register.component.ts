import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../../services/register.service';
import { Router } from '@angular/router';
import { MessegeModelService } from '../../services/messege-model.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [RegisterService],
})
export class RegisterComponent implements OnInit {

  _first_name = '';
  _last_name = '';
  _email = '';
  _password = '';
  _confirm_password = '';

  constructor(private _registerService: RegisterService, private _router: Router, private messege: MessegeModelService) { }

  set first_name(fn: string) {
    this._first_name = fn;
  }

  set last_name(ln: string) {
    this._last_name = ln;
  }

  set email(em: string) {
    this._email = em;
  }

  set password(pw: string) {
    this._password = pw;
  }

  set confirm_password(cpw: string) {
    this._confirm_password = cpw;
  }

  get first_name() {
    return this._first_name;
  }

  get last_name() {
    return this._last_name;
  }

  get email() {
    return this._email;
  }

  get password() {
    return this._password;
  }

  get confirm_password() {
    return this._confirm_password;
  }

  ngOnInit() {
  }

  clickRegister(): void {
    // console.log('clicked register');

    if (this.email === '') {
      this.messege.error = true;
      this.messege.messege = 'Please enter a valid email';
      this.messege.show = true;
      return;
    }
    if (this.password === '') {
      this.messege.error = true;
      this.messege.messege = 'Please enter a valid password';
      this.messege.show = true;
      return;
    }
    if (this.password !== this.confirm_password) {
      this.messege.error = true;
      this.messege.messege = 'Passwords must match';
      this.messege.show = true;
      return;
    }
    if (this.first_name === '') {
      this.messege.error = true;
      this.messege.messege = 'Please enter a valid first name';
      this.messege.show = true;
      return;
    }
    if (this.last_name === '') {
      this.messege.error = true;
      this.messege.messege = 'Please enter a valid last name';
      this.messege.show = true;
      return;
    }
    this._registerService.postRegister(this.email, this.password, this.confirm_password, this.first_name, this.last_name).subscribe(
      data => {
        this.messege.error = false;
        this.messege.messege = 'Please activate your account from your email';
        this.messege.show = true;
    });
  }


}
