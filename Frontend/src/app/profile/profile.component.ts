import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IUser } from '../interface/iuser';
import { ProfileService } from '../services/profile.service';

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
  constructor(private _profileService: ProfileService) { }

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
    return this._firstName;
  }

  get lastName () {
    return this._lastName;
  }

  get email () {
    return this._email;
  }

  ngOnInit() {
  }

  clickProfile(): void {
    console.log('we clicked login');
    this._profileService.getProfile(this.firstName, this.lastName, this.email).subscribe(data => {
      console.log(data);
    });
  }

}
