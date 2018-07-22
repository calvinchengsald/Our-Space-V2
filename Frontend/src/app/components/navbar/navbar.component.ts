import { Component, OnInit,  AfterContentChecked } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  title: 'Our Space';
  _name: string;

  constructor(private _loginService: LoginService, private _router: Router) {
  }

  get loginService() {
    return this._loginService;
  }

  set messegeMessege (pw: string) {
    this._name = pw;
  }
  get messegeMessege() {
    return this._name;
  }


  ngOnInit() {

  }


  clickLogout(): void {
    console.log('we clicked logout');
    // need to do a req, but for now just set vars client side
    this._loginService.logout().subscribe(  // code inside of subscribe not executing
      res => {
        console.log('logged out');
      }
    );
    this._loginService.isLoggedIn = false;
    this._router.navigateByUrl('/');
    // this._loginService.getLogin(this.email, this.password).subscribe(data => {
    //   if (!data['password'] ) {
    //     this._messegeService.error = true;
    //     this._messegeService.show = true;
    //     this._messegeService.messege = data['email'];
    //     console.log('in pass');
    //   } else if (data['email'] ) {
    //     this._messegeService.error = false;
    //     this._messegeService.show = false;
    //     this._messegeService.messege = data['email'];
    //     this._loginService.firstName = data['firstName'];
    //     this._loginService.lastName = data['lastName'];
    //     this._loginService.email = data['email'];
    //     this._loginService.password = data['password'];
    //     LoginService.isLoggedIn = true;
    //     this._router.navigateByUrl('/');
    //   } else {

    //   }
    // });
  }
}
