import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-reset-page',
  templateUrl: './reset-page.component.html',
  styleUrls: ['./reset-page.component.css']
})
export class ResetPageComponent implements OnInit {

  _email: string;
  constructor(private _loginService: LoginService) { }

  set email(em: string) {
    this._email = em;
  }

  get email() {
    return this._email;
  }
  ngOnInit() {
  }

  clickReset(): void {
    console.log('clicked register');
    this._loginService.changePass(this.email).subscribe(
      data => {
        console.log(data);
      });
  }
}
