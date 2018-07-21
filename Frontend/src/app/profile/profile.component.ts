import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IUser } from '../interface/iuser';
import { ProfileService } from '../services/profile.service';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  providers: [ProfileService]
})
export class ProfileComponent implements OnInit {

  _firstName: string;
  _lastName: string;
  _email: string;

  // post stuff for user will be in here somewhere or something
  constructor(private _profileService: ProfileService, private _loginService: LoginService) {

   }

  set firstName (fn: string) {
    this._firstName = fn;
  }

  set lastName (ln: string) {
    this._lastName = ln;
  }

  set email (em: string) {
    this._email = em;
  }

  get firstName () {
    console.log('in first name');
    console.log('Name = ' + this._profileService.firstName );
    return this._profileService.firstName;
  }

  get lastName () {
    return this._lastName;
  }

  get email () {
    return this._email;
  }


  setValues(user: string) {
    this._firstName = user['firstName'];
    console.log(this._firstName);
    this._lastName = user['lastName'];
    this._email = user['email'];

  }

  ngOnInit() {
    console.log('we clicked profile');
    this._profileService.getProfile(this._loginService.firstName, this._loginService.lastName,
      this._loginService.email).subscribe(data => this.setValues(data));
  }

  clickProfile(): void {

  }

}
