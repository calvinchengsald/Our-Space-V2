import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  first_name: string;
  last_name: string;
  email: string;
  password: string;
  confirm_password: string;

  constructor() { }

  ngOnInit() {
  }

  clickRegister(): void {
    console.log(this.first_name);
    console.log(this.last_name);
    console.log(this.email);
    console.log(this.confirm_password);
    console.log(this.password);
    console.log('clicked register');
  }


}
