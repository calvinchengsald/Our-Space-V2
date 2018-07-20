import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [RegisterService],
})
export class RegisterComponent implements OnInit {

  _first_name: string;
  _last_name: string;
  _email: string;
  _password: string;
  _confirm_password: string;

  constructor(private _registerService: RegisterService) { }

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
    console.log('clicked register');
    this._registerService.postRegister(this.email, this.password, this.confirm_password, this.first_name, this.last_name).subscribe(
      data => {console.log(data);
    });
  }


}
