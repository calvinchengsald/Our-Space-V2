import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import { IUser } from '../../interface/iuser';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  filteredUsers: IUser[];
  usersList: IUser[];
  _listFilter = '';
  sorted = { // 0: not sorted; 1:in asscending order; -1 in descending order
    first_name: 0, last_name: 0, email: 0
  };

  get listFilter() {
    return this._listFilter;
  }

  set listFilter(temp: string) {
    if (this._loginService.isLoggedIn) {
      this._loginService.getAllUsers();
      this.usersList = this._loginService.allUser;
      this.filteredUsers = this.usersList;
      this._listFilter = temp;
      this.filteredUsers = this._listFilter ? this.performFilter(this._listFilter) : this.usersList;
    }
  }

  // filter names
  performFilter(filterBy: string): IUser[] {
    this._loginService.getAllUsers();
    this.usersList = this._loginService.allUser;
    this.filteredUsers = this.usersList;
    filterBy = filterBy.toLocaleLowerCase();
    return this.usersList.filter((user: IUser) =>
      user.first_name.toLocaleLowerCase().indexOf(filterBy) !== -1 || user.last_name.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }



  constructor(private _loginService: LoginService, public router: Router) {  }

  get loginService() {
    return this._loginService;
  }

  ngOnInit() {
    this._loginService.checkLogin(); // for refreshing on the pae
    this._loginService.getAllUsers();
    this.usersList = this._loginService.allUser;
    this.filteredUsers = this.usersList;
    console.log('users = ' + this.filteredUsers);
  }

  viewUser(input) {
    this.router.navigateByUrl(`/profile/${input}`);
  }

  highlight(event: MouseEvent) {
    event.srcElement.setAttribute('style', 'background-color:rgb(225, 225, 225);');
  }
  dehighlight(event: MouseEvent) {
    event.srcElement.removeAttribute('style');
  }

  sortUsers(on) {
    const i: number = this.sorted[on] === 1 ? -1 : 1;
    this.sorted[on] = this.sorted[on] === 0 ? 1 : this.sorted[on] * -1;
    this.filteredUsers.sort((a, b) => {
      if (a[on].toUpperCase() > b[on].toUpperCase()) {
        if (a === undefined) { return 1 * i; }
        if (b === undefined) { return -1 * i; }
        return -1 * i;
      }
      if (a[on].toUpperCase() < b[on].toUpperCase()) {
        if (a === undefined) { return 1 * i; }
        if (b === undefined) { return -1 * i; }
        return 1 * i;
      }
      return 0;
    });
  }
}
