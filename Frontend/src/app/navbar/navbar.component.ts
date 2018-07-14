import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  title:string = 'Our Space';

  get loginService(){
    return this._loginService;
  }

  constructor(private _loginService :LoginService) { }

  ngOnInit() {
  }

}
